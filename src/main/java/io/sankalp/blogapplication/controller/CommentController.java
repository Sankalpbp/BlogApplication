package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.CommentDTO;
import io.sankalp.blogapplication.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api" )
@Tag(
    name = "CRUD REST APIs for Comment resource"
)
public class CommentController {

    private CommentService commentService;

    public CommentController ( CommentService commentService ) {
        this.commentService = commentService;
    }

    @Operation(
        summary = "Create Comment REST API",
        description = "Create Comment REST API is used to create a new Comment associated to a particular Post"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @PostMapping ( "/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment ( @PathVariable ( value = "postId" ) Long postId,
                                                      @Valid @RequestBody CommentDTO commentDTO ) {
        return new ResponseEntity<> ( commentService.createComment ( postId, commentDTO ), HttpStatus.CREATED );
    }

    @Operation (
        summary = "GET All Comments related to the given postId",
        description = "GET All Comments REST API is used to get all the Comments related to a particular Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping ( "/posts/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId ( @PathVariable ( value = "postId" ) Long postId ) {
        return ResponseEntity.ok ( commentService.getCommentsByPostId ( postId ) );
    }

    @Operation (
        summary = "GET a Comment with the given Id related to the given postId",
        description = "GET Comment REST API is used to get a particular Comment related to a particular Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping ( "/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById ( @PathVariable ( value = "id" ) Long commentId,
                                                       @PathVariable ( value = "postId" ) Long postId ) {
        return ResponseEntity.ok ( commentService.getCommentById ( commentId, postId ) );
    }

    @Operation (
        summary = "Update a Comment with the given Id related to the given postId",
        description = "Update a Comment REST API is used to get all the Comments related to a particular Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping ( "/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateCommentById ( @Valid @RequestBody CommentDTO comment,
                                                          @PathVariable ( value = "id") Long commentId,
                                                          @PathVariable ( value = "postId" ) Long postId ) {
        return ResponseEntity.ok ( commentService.updateComment ( comment, postId, commentId ) );
    }

    @Operation (
        summary = "Delete a Comment with the given Id related to the given postId",
        description = "GET Comment REST API is used to get a particular Comment related to a particular Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping ( "/posts/{postId}/comments/{id}" )
    public ResponseEntity<String> deleteCommentById ( @PathVariable ( value = "postId" ) Long postId,
                                                      @PathVariable ( value = "id" ) Long commentId ) {
        return ResponseEntity.ok ( commentService.deleteCommentById ( commentId, postId ) );
    }

}
