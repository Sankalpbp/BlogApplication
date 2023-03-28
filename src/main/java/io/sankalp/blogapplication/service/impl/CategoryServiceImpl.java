package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Category;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.CategoryDTO;
import io.sankalp.blogapplication.repository.CategoryRepository;
import io.sankalp.blogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl ( CategoryRepository categoryRepository,
                                 ModelMapper mapper ) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO addCategory ( CategoryDTO categoryDTO ) {

        Category category = mapper.map ( categoryDTO, Category.class );
        Category savedCategory = categoryRepository.save ( category );

        return mapper.map ( savedCategory, CategoryDTO.class );
    }

    @Override
    public CategoryDTO getCategoryById ( Long id ) {
        Category category = categoryRepository.findById ( id )
                .orElseThrow ( () -> new ResourceNotFoundException ( "category",
                        "id",
                        id.toString () ) );

        return mapper.map ( category, CategoryDTO.class );
    }

    @Override
    public List<CategoryDTO> getAllCategories ( ) {
        List<Category> categories = categoryRepository.findAll ( );

        return categories.stream ()
                .map ( ( category ) -> mapper.map ( category, CategoryDTO.class ) )
                .collect ( Collectors.toList () );
    }

    @Override
    public CategoryDTO updateCategory ( CategoryDTO categoryDTO, Long id ) {
        Category category = categoryRepository.findById ( id )
                .orElseThrow ( () -> new ResourceNotFoundException ( "category",
                        "id",
                        id.toString () ) );

        category.setName ( categoryDTO.getName () );
        category.setDescription ( categoryDTO.getDescription () );

        Category updatedCategory = categoryRepository.save ( category );

        return mapper.map ( updatedCategory, CategoryDTO.class );
    }

    @Override
    public String deleteCategoryById ( Long id ) {

        Category category = categoryRepository.findById ( id )
                        .orElseThrow ( () -> new ResourceNotFoundException ( "category",
                                                                             "id",
                                                                             id.toString () ) );

        categoryRepository.deleteById ( id );

        return "Category Deleted Successfully!";
    }

}
