package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost ( PostDTO post );

    List<PostDTO> getAllPosts ( int pageNumber, int pageSize );

    PostDTO getPostById ( Long id );

    PostDTO updatePost ( PostDTO post, Long id );

    String deletePostById ( Long id );
}
