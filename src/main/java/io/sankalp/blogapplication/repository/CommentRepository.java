package io.sankalp.blogapplication.repository;

import io.sankalp.blogapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPostId ( Long postId );
}
