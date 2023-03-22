package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl ( PostRepository postRepository ) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost ( PostDTO post ) {
        Post newPost = dtoToEntity ( post );
        Post createdPost = postRepository.save ( newPost );

        return entityToDTO ( createdPost );
    }

    public PostDTO getPostById ( Long id ) {
        Post post = postRepository.findById ( id )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                                       "id",
                                                                                       id.toString () ) );

        return entityToDTO ( post );
    }

    public PostDTO updatePost ( PostDTO postDTO, Long id ) {
        Post post = postRepository.findById ( id )
                                  .orElseThrow ( () -> new ResourceNotFoundException ( "Post",
                                                                                       "id",
                                                                                       id.toString () ) );

        post.setTitle ( postDTO.getTitle () );
        post.setDescription( postDTO.getDescription () );
        post.setContent ( postDTO.getContent () );

        Post updatedPost = postRepository.save ( post );
        return entityToDTO ( updatedPost );
    }

    public String deletePostById ( Long id ) {
        postRepository.deleteById ( id );

        return "Post Deleted successfully!";
    }

    public PostResponse getAllPosts ( int pageNumber, int pageSize ) {

        Pageable pageable = PageRequest.of ( pageNumber, pageSize );
        Page<Post> pageOfPosts = postRepository.findAll ( pageable );

        List<Post> posts = pageOfPosts.getContent ();

        List<PostDTO> content = posts.stream ( )
                                     .map ( this::entityToDTO )
                                     .toList ();

        PostResponse postResponse = new PostResponse ();
        postResponse.setContent ( content );
        postResponse.setPageNumber ( pageNumber );
        postResponse.setTotalElements ( postRepository.count () );
        postResponse.setPageSize ( pageSize );
        postResponse.setTotalPages ( pageOfPosts.getTotalPages () );
        postResponse.setLast ( pageOfPosts.isLast () );

        return postResponse;
    }

    private PostDTO entityToDTO ( Post post ) {
        PostDTO postDTO = new PostDTO ( );

        postDTO.setId ( post.getId ( ) );
        postDTO.setTitle ( post.getTitle ( ) );
        postDTO.setDescription ( post.getDescription ( ) );
        postDTO.setContent ( post.getContent ( ) );

        return postDTO;
    }

    private Post dtoToEntity ( PostDTO postDTO ) {
        Post post = new Post ();

        post.setId ( postDTO.getId ( ) );
        post.setTitle ( postDTO.getTitle ( ) );
        post.setDescription ( postDTO.getDescription ( ) );
        post.setContent ( postDTO.getContent ( ) );

        return post;
    }

}
