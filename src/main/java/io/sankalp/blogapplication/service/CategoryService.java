package io.sankalp.blogapplication.service;

import io.sankalp.blogapplication.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO addCategory ( CategoryDTO category );

    public CategoryDTO getCategoryById ( Long id );

    public List<CategoryDTO> getAllCategories ( );

    public CategoryDTO updateCategory ( CategoryDTO category, Long id );

    public String deleteCategoryById ( Long id );

}
