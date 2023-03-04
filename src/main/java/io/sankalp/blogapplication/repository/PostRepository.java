package io.sankalp.blogapplication.repository;

import io.sankalp.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
