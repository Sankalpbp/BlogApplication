package io.sankalp.blogapplication.service.impl;

import io.sankalp.blogapplication.entity.Category;
import io.sankalp.blogapplication.exception.ResourceNotFoundException;
import io.sankalp.blogapplication.payload.CategoryDTO;
import io.sankalp.blogapplication.repository.CategoryRepository;
import io.sankalp.blogapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

}
