package com.codex.mystore.controller.task;

import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.TaskRepo;
import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.tasks.Task;
import com.codex.mystore.network.request.RoleRequest;
import com.codex.mystore.network.request.TaskRequest;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    TaskRepo taskRepo;

    @Autowired
    DateUtils dateUtils;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRole(@RequestBody TaskRequest taskRequest) {
        String currentDateTime = dateUtils.currentDateAndTime();
        Task task = new Task();
        task.setTaskTitle(taskRequest.getTaskTitle());
        task.setTaskDescription(taskRequest.getTaskDescription());
        task.setCreateAt(currentDateTime);

        taskRepo.save(task);
        return ResponseEntity.ok("Create Task Success");
    }
}
