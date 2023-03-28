package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.CategoryDTO;
import io.sankalp.blogapplication.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ( "/api/categories" )
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController ( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize ( "hasRole( 'ADMIN' )" )
    public ResponseEntity<CategoryDTO> addCategory ( @RequestBody CategoryDTO category ) {
        return new ResponseEntity<> ( categoryService.addCategory ( category ), HttpStatus.CREATED );
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<CategoryDTO> getCategoryById ( @PathVariable Long id ) {
        return ResponseEntity.ok ( categoryService.getCategoryById ( id ) );
    }

}
