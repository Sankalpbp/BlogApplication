package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<PostDTO> getAllPosts ( ) {
        List<Post> allPosts = postRepository.findAll ();
        return allPosts.stream ()
                       .map ( this::entityToDTO )
                       .collect ( Collectors.toList ( ) );
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
