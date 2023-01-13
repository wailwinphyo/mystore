package com.codex.mystore.controller.project;

import com.codex.mystore.models.project.Project;
import com.codex.mystore.network.request.ProjectRequest;
import com.codex.mystore.services.ProjectService;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    DateUtils dateUtils;
    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/createProject")
    public ResponseEntity<?> createRole(@RequestBody ProjectRequest projectRequest) {
        String currentDateTime = dateUtils.currentDateAndTime();

        Project project = new Project();
        project.setDescription(projectRequest.getProjectDescription());
        project.setStartDate(currentDateTime);
        project.setEndDate(currentDateTime);
        project.setUpdateAt(currentDateTime);
        project.setCreateAt(currentDateTime);
        return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
    }
}
