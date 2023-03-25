package io.sankalp.blogapplication.repository;

import io.sankalp.blogapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
