package com.codex.mystore.controller.team;

import com.codex.mystore.dao.repo.CommentRepo;
import com.codex.mystore.dao.repo.TeamRepo;
import com.codex.mystore.models.tasks.Task;
import com.codex.mystore.models.team.Team;
import com.codex.mystore.network.request.TaskRequest;
import com.codex.mystore.network.request.TeamRequest;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/team")
public class TeamController {

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    DateUtils dateUtils;

    @PostMapping("createTeam")
    public ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest) {
        String currentDateTime = dateUtils.currentDateAndTime();
        Team team = new Team();

        team.setTeamName(teamRequest.getTeamName());
        team.setCreateAt(currentDateTime);

        teamRepo.save(team);
        return ResponseEntity.ok("Create Task Success");
    }
}
