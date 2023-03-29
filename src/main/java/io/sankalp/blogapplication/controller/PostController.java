package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.payload.PostResponse;
import io.sankalp.blogapplication.service.PostService;
import io.sankalp.blogapplication.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/posts" )
public class PostController {

    private PostService postService;

    public PostController ( PostService postService ) {
        this.postService = postService;
    }

    @PostMapping
    @PreAuthorize ( "hasRole ( 'ADMIN' )" )
    public ResponseEntity<PostDTO> createPost ( @Valid @RequestBody PostDTO post ) {
        return new ResponseEntity<> ( postService.createPost ( post ), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts (
            @RequestParam ( value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false ) int pageNumber,
            @RequestParam ( value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false ) int pageSize,
            @RequestParam ( value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false ) String sortBy,
            @RequestParam ( value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false ) String sortDir
    ) {
        return new ResponseEntity<> ( postService.getAllPosts ( pageNumber, pageSize, sortBy, sortDir ), HttpStatus.OK );
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<PostDTO> getPostById ( @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.getPostById ( id ) );
    }

    @PutMapping ( "/{id}" )
    @PreAuthorize ( "hasRole ( 'ADMIN' )")
    public ResponseEntity<PostDTO> updatePost ( @Valid @RequestBody PostDTO post, @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.updatePost ( post, id ) );
    }

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
