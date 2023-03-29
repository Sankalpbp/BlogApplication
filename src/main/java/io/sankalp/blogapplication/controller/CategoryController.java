package io.sankalp.blogapplication.controller;

import io.sankalp.blogapplication.payload.CategoryDTO;
import io.sankalp.blogapplication.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ( "/api/categories" )
@Tag (
    name = "CRUD REST APIs for Category resource"
)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController ( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @Operation(
        summary = "Add new Category REST API",
        description = "REST API for adding new Category"
    )
    @ApiResponse (
        responseCode = "201",
        description = "HTTP Status code 201 CREATED"
    )
    @PostMapping
    @PreAuthorize ( "hasRole( 'ADMIN' )" )
    public ResponseEntity<CategoryDTO> addCategory ( @RequestBody CategoryDTO category ) {
        return new ResponseEntity<> ( categoryService.addCategory ( category ), HttpStatus.CREATED );
    }

    @Operation (
        summary = "GET Category by Id REST API",
        description = "GET Category by Id REST API is used to fetch a single Category from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping ( "/{id}" )
    public ResponseEntity<CategoryDTO> getCategoryById ( @PathVariable Long id ) {
        return ResponseEntity.ok ( categoryService.getCategoryById ( id ) );
    }

    @Operation (
        summary = "GET All Categories REST API",
        description = "GET All Categories REST API is used to all the Categories from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories ( ) {
        return ResponseEntity.ok ( categoryService.getAllCategories () );
    }

    @Operation (
        summary = "UPDATE Category by Id REST API",
        description = "UPDATE Category by Id REST API is used to update a single Category from database"
    )
    @ApiResponse (
        responseCode = "200",
        description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping ( "/update/{id}" )
    @PreAuthorize ( "hasRole ( 'ADMIN' )" )
    public ResponseEntity<CategoryDTO> updateCategory ( @RequestBody CategoryDTO category, @PathVariable Long id ) {
        return ResponseEntity.ok ( categoryService.updateCategory ( category, id ) );
    }

    @Operation (
        summary = "DELETE Category by Id REST API",
        description = "DELETE Category by Id REST API is used to delete a single Category from database"
    )
    @ApiResponse (
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping ( "/delete/{id}" )
    @PreAuthorize ( "hasRole ( 'ADMIN' )" )
    public ResponseEntity<String> deleteCategory ( @PathVariable Long id ) {
        return ResponseEntity.ok ( categoryService.deleteCategoryById ( id ) );
    }

}
