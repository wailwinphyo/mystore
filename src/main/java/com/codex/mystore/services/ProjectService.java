package com.codex.mystore.services;

import com.codex.mystore.models.project.Project;

import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project);

    Optional<Project> getProject(Long id);

}
