package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Comment;
import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.exception.BlogAPIException;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.CommentDTO;
import io.sankalp.blogapplication.repository.CommentRepository;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl ( CommentRepository commentRepository,
                                PostRepository postRepository,
                                ModelMapper mapper ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment ( Long postId, CommentDTO commentDTO ) {

        Comment comment = dtoToEntity ( commentDTO );

        Post post = postRepository.findById ( postId )
                .orElseThrow ( () -> new ResourceNotFoundException( "Post",
                                                                     "id",
                                                                     postId.toString () ) );

        comment.setPost ( post );
        Comment createdComment = commentRepository.save ( comment );

        return entityToDTO ( createdComment );
    }

    @Override
    public List<CommentDTO> getCommentsByPostId ( Long postId ) {
        return commentRepository.findByPostId ( postId )
                                .stream ()
                                .map ( this::entityToDTO )
                                .toList ();
    }

    @Override
    public CommentDTO getCommentById ( Long commentId, Long postId ) {
        Comment comment = commentRepository.findById ( commentId )
                .orElseThrow ( () -> new ResourceNotFoundException ( "comment",
                                                                     "commentId",
                                                                     commentId.toString () ) );

        Post post = postRepository.findById ( postId )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "post",
                                                                                       "postId",
                                                                                       postId.toString () ) );

        if ( !comment.getPost ().getId ().equals ( post.getId () ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST,
                    "Comment does not belong to the postId mentioned." );
        }

        return entityToDTO ( comment );
    }

    @Override
    public CommentDTO updateComment ( CommentDTO commentDTO, Long postId, Long commentId ) {
        Comment comment = commentRepository.findById ( commentId )
                .orElseThrow ( () -> new ResourceNotFoundException ( "Comment",
                                                                     "commentId",
                                                                     commentId.toString () ) );

        Post post = postRepository.findById ( postId )
                .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                     "postId",
                                                                     postId.toString () ) );

        if ( !comment.getPost ().getId ().equals ( post.getId () ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST,
                    "Comment does not belong to the postId mentioned." );
        }

        comment.setName ( commentDTO.getName () );
        comment.setBody ( commentDTO.getBody () );
        comment.setEmail ( commentDTO.getEmail () );

        Comment updatedComment = commentRepository.save ( comment );

        return entityToDTO ( updatedComment );
    }

    @Override
    public String deleteCommentById ( Long commentId, Long postId ) {
        validateCommentUsingPost ( commentId, postId );
        commentRepository.deleteById ( commentId );
        return "Comment related to postId: " + postId + " has been successfully deleted.";
    }

    private void validateCommentUsingPost (Long commentId, Long postId ) {
        Post post = getPost ( postId );
        Comment comment = getComment ( commentId );

        if ( !comment.getPost ().getId ().equals ( postId ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST,
                    "Comment does not belong to the given postId" );
        }
    }

    private Post getPost ( Long postId ) {
        return postRepository.findById ( postId )
                             .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                                  "postId",
                                                                                  postId.toString () ) );
    }

    private Comment getComment ( Long commentId ) {
        return commentRepository.findById ( commentId )
                                .orElseThrow ( () -> new ResourceNotFoundException ( "Comment",
                                                                                     "commentId",
                                                                                     commentId.toString () ) );
    }

    private Comment dtoToEntity ( CommentDTO commentDTO ) {
        return mapper.map ( commentDTO, Comment.class );
    }

    private CommentDTO entityToDTO ( Comment comment ) {
        return mapper.map ( comment, CommentDTO.class );
    }
}
