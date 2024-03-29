package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost ( PostDTO post );

    PostResponse getAllPosts (int pageNumber, int pageSize, String sortBy, String sortDir );

    PostDTO getPostById ( Long id );

    PostDTO updatePost ( PostDTO post, Long id );

    String deletePostById ( Long id );

    List<PostDTO> getPostsByCategoryId ( Long categoryId );
}
