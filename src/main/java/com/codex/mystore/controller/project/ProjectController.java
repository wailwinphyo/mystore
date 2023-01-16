package com.codex.mystore.controller.project;

import com.codex.mystore.models.project.Project;
import com.codex.mystore.network.request.EditProjectRequest;
import com.codex.mystore.network.request.ProjectRequest;
import com.codex.mystore.services.ProjectService;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    DateUtils dateUtils;
    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/createProject")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest projectRequest) {
        String currentDateTime = dateUtils.currentDateAndTime();

        Project project = new Project();

        project.setProjectName(projectRequest.getProjectName());
        project.setDescription(projectRequest.getProjectDescription());
        project.setStartDate(currentDateTime);
        project.setEndDate(currentDateTime);
        project.setUpdateAt(currentDateTime);
        project.setCreateAt(currentDateTime);
        return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/editProject")
    public ResponseEntity<?> createProject(@RequestBody EditProjectRequest editProjectRequest) {

        String currentDateTime = dateUtils.currentDateAndTime();

        Optional<Project> project = projectService.getProject(editProjectRequest.getProjectId());

        if(project.isPresent()){
            Project tempProject = project.get();
            tempProject.setProjectName(editProjectRequest.getProjectName());
            tempProject.setDescription(editProjectRequest.getProjectDescription());
            tempProject.setStartDate(currentDateTime);
            tempProject.setEndDate(currentDateTime);
            tempProject.setUpdateAt(currentDateTime);
        }
        return new ResponseEntity<>("Update Project Successfully", HttpStatus.OK);
    }


}
