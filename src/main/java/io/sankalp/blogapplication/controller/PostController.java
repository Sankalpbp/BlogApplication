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
    public ResponseEntity<List<PostDTO>> getAllPosts ( ) {
        return new ResponseEntity<> ( postService.getAllPosts ( ), HttpStatus.OK );
    }

}
