package com.codex.mystore.dao.repo;

import com.codex.mystore.models.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
