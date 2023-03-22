package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.PostDTO;
import io.sankalp.blogapplication.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PostDTO> createPost ( @RequestBody PostDTO post ) {
        return new ResponseEntity<> ( postService.createPost ( post ), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts (
            @RequestParam ( value = "pageNumber", defaultValue = "0", required = false ) int pageNumber,
            @RequestParam ( value = "pageSize", defaultValue = "5", required = false ) int pageSize
    ) {
        return new ResponseEntity<> ( postService.getAllPosts ( pageNumber, pageSize ), HttpStatus.OK );
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<PostDTO> getPostById ( @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.getPostById ( id ) );
    }

    @PutMapping ( "/{id}" )
    public ResponseEntity<PostDTO> updatePost ( @RequestBody PostDTO post, @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.updatePost ( post, id ) );
    }

    @DeleteMapping ( "/{id}" )
    public ResponseEntity<String> deletePostById ( @PathVariable ( name = "id" ) Long id ) {
        return ResponseEntity.ok ( postService.deletePostById ( id ) );
    }

}
