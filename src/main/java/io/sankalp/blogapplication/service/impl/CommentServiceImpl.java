package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Comment;
import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.exception.BlogAPIException;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.CommentDTO;
import io.sankalp.blogapplication.repository.CommentRepository;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl ( CommentRepository commentRepository, PostRepository postRepository ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
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

    public List<CommentDTO> getCommentsByPostId ( Long postId ) {
        return commentRepository.findByPostId ( postId )
                                .stream ()
                                .map ( this::entityToDTO )
                                .toList ();
    }

    public CommentDTO getCommentById ( Long commentId, Long postId ) {
        Comment comment = commentRepository.findById ( commentId )
                .orElseThrow ( () -> new ResourceNotFoundException ( "comment",
                                                                     "id",
                                                                     commentId.toString () ) );

        Post post = postRepository.findById ( postId )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "post",
                                                                                       "id",
                                                                                       postId.toString () ) );

        if ( !comment.getPost ().getId ().equals ( post.getId () ) ) {
            throw new BlogAPIException ( HttpStatus.BAD_REQUEST,
                    "Comment does not belong to the postId mentioned." );
        }

        return entityToDTO ( comment );
    }

    private Comment dtoToEntity ( CommentDTO commentDTO ) {
        Comment comment = new Comment ();

        comment.setBody ( commentDTO.getBody () );
        comment.setEmail ( commentDTO.getEmail () );
        comment.setId ( commentDTO.getId () );
        comment.setName ( commentDTO.getName () );

        return comment;
    }

    private CommentDTO entityToDTO ( Comment comment ) {
        CommentDTO commentDTO = new CommentDTO ();
        commentDTO.setBody ( comment.getBody () );
        commentDTO.setEmail ( comment.getEmail () );
        commentDTO.setId ( comment.getId () );
        commentDTO.setName ( comment.getName () );

        return commentDTO;
    }
}
