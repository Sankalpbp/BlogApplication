package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.CommentDTO;
import io.sankalp.blogapplication.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api" )
public class CommentController {

    private CommentService commentService;

    public CommentController ( CommentService commentService ) {
        this.commentService = commentService;
    }

    @PostMapping ( "/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment ( @PathVariable ( value = "postId" ) Long postId,
                                                      @Valid @RequestBody CommentDTO commentDTO ) {
        return new ResponseEntity<> ( commentService.createComment ( postId, commentDTO ), HttpStatus.CREATED );
    }

    @GetMapping ( "/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId ( @PathVariable ( value = "postId" ) Long postId ) {
        return commentService.getCommentsByPostId ( postId );
    }

    @GetMapping ( "/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById ( @PathVariable ( value = "id" ) Long commentId,
                                                       @PathVariable ( value = "postId" ) Long postId ) {
        return ResponseEntity.ok ( commentService.getCommentById ( commentId, postId ) );
    }

    @PutMapping ( "/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateCommentById ( @Valid @RequestBody CommentDTO comment,
                                                          @PathVariable ( value = "id") Long commentId,
                                                          @PathVariable ( value = "postId" ) Long postId ) {
        return ResponseEntity.ok ( commentService.updateComment ( comment, postId, commentId ) );
    }

    @DeleteMapping ( "/posts/{postId}/comments/{id}" )
    public ResponseEntity<String> deleteCommentById ( @PathVariable ( value = "postId" ) Long postId,
                                                      @PathVariable ( value = "id" ) Long commentId ) {
        return ResponseEntity.ok ( commentService.deleteCommentById ( commentId, postId ) );
    }

}
