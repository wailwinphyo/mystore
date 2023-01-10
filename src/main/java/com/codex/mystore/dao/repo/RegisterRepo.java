package com.codex.mystore.dao.repo;

import com.codex.mystore.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<User, Long> {
}
