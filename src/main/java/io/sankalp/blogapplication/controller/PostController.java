package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;
import io.sankalp.blogapplication.service.PostService;
import io.sankalp.blogapplication.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/posts" )
@Tag (
    name = "CRUD REST APIs for Post resource"
)
public class PostController {

    private PostService postService;

    public PostController ( PostService postService ) {
        this.postService = postService;
    }

    @Operation (
        summary = "Create Post REST API",
        description = "Create Post REST API is used to create a new Post"
    )
    @ApiResponse (
        responseCode = "201",
        description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement (
        name = "Bear Authentication"
    )
    @PostMapping
    @PreAuthorize ( "hasRole ( 'ADMIN' )" )
    public ResponseEntity<PostDTO> createPost ( @Valid @RequestBody PostDTO post ) {
        return new ResponseEntity<> ( postService.createPost ( post ), HttpStatus.CREATED );
    }

    @Operation (
        summary = "GET All Posts REST API",
        description = "GET All Posts REST API is used to get all the Posts from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts (
            @RequestParam ( value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false ) int pageNumber,
            @RequestParam ( value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false ) int pageSize,
            @RequestParam ( value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false ) String sortBy,
            @RequestParam ( value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false ) String sortDir
    ) {
        return new ResponseEntity<> ( postService.getAllPosts ( pageNumber, pageSize, sortBy, sortDir ), HttpStatus.OK );
    }

    @Operation (
        summary = "GET Post by Id REST API",
        description = "GET Post by Id REST API is used to fetch a single Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping ( "/{id}" )
    public ResponseEntity<PostDTO> getPostById ( @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.getPostById ( id ) );
    }

    @Operation (
        summary = "UPDATE Post by Id REST API",
        description = "UPDATE Post by Id REST API is used to update a single Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @SecurityRequirement (
        name = "Bear Authentication"
    )
    @PutMapping ( "/{id}" )
    @PreAuthorize ( "hasRole ( 'ADMIN' )")
    public ResponseEntity<PostDTO> updatePost ( @Valid @RequestBody PostDTO post, @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.updatePost ( post, id ) );
    }

    @Operation (
        summary = "DELETE Post by Id REST API",
        description = "DELETE Post by Id REST API is used to delete a single Post from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @SecurityRequirement (
        name = "Bear Authentication"
    )
    @DeleteMapping ( "/{id}" )
    @PreAuthorize ( " hasRole ( 'ADMIN' )")
    public ResponseEntity<String> deletePostById ( @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.deletePostById ( id ) );
    }

    @GetMapping ( "/getPostsByCategoryId/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostsByCategoryId ( @PathVariable ( value = "categoryId" ) Long categoryId ) {
        return ResponseEntity.ok ( postService.getPostsByCategoryId ( categoryId ) );
    }

}
