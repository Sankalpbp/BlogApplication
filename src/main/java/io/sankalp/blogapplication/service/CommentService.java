package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO createComment ( Long postId, CommentDTO comment );

    public List<CommentDTO> getCommentsByPostId ( Long postId );
}
