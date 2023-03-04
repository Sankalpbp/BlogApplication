package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost ( PostDTO post );

    List<PostDTO> getAllPosts ( );
}
