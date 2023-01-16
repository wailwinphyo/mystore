package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.ProjectRepo;
import com.codex.mystore.models.project.Project;
import com.codex.mystore.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectImpl implements ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }
}
