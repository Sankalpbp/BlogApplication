package io.sankalp.blogapplication.repository;

import io.sankalp.blogapplication.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
