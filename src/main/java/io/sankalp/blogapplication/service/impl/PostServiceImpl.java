package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Post;
import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.repository.PostRepository;
import io.sankalp.blogapplication.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl ( PostRepository postRepository ) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost ( PostDTO post ) {
        Post newPost = new Post();
        newPost.setTitle ( post.getTitle ( ) );
        newPost.setDescription ( post.getDescription ( ) );
        newPost.setContent ( post.getContent ( ) );

        Post createdPost = postRepository.save ( newPost );

        PostDTO postResponse = new PostDTO ();
        postResponse.setId ( createdPost.getId () );
        postResponse.setTitle ( createdPost.getTitle ( ) );
        postResponse.setDescription ( createdPost.getDescription ( ) );
        postResponse.setContent ( createdPost.getContent ( ) );

        return postResponse;
    }

    public List<PostDTO> getAllPosts ( ) {
        List<Post> allPosts = postRepository.findAll ();
        List<PostDTO> allPostsResponse = new ArrayList<>();

        for ( Post post : allPosts ) {
            PostDTO newPost = new PostDTO ();

            newPost.setId ( post.getId ( ) );
            newPost.setTitle ( post.getTitle () );
            newPost.setDescription ( post.getDescription ( ) );
            newPost.setContent ( post.getContent ( ) );

            allPostsResponse.add ( newPost );
        }

        return allPostsResponse;
    }

}
