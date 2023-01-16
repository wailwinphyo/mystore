package com.codex.mystore.dao.repo;

import com.codex.mystore.models.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
