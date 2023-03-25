package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.CommentDTO;

public interface CommentService {

    public CommentDTO createComment ( Long postId, CommentDTO comment );

}
