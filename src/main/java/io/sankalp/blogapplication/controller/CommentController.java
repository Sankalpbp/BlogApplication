package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.CommentDTO;
import io.sankalp.blogapplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api" )
public class CommentController {

    private CommentService commentService;

    public CommentController ( CommentService commentService ) {
        this.commentService = commentService;
    }

    @PostMapping ( "/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment (@PathVariable ( value = "postId" ) Long postId,
                                                     @RequestBody CommentDTO commentDTO ) {
        return new ResponseEntity<> ( commentService.createComment ( postId, commentDTO ), HttpStatus.CREATED );
    }

}
