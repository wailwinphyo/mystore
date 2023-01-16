package com.codex.mystore.controller.guest;

import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.models.project.Project;
import com.codex.mystore.network.request.GuestProjectRequest;
import com.codex.mystore.network.request.GuestRequest;
import com.codex.mystore.services.GuestService;
import com.codex.mystore.services.ProjectService;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @Autowired
    DateUtils dateUtils;

    @Autowired
    ProjectService projectService;

    @PutMapping("/create")
    public ResponseEntity<?> createGuest(@RequestBody GuestRequest guestRequest) {
        Guest guest = guestService.findByEmail(guestRequest.getGuestEmail());
        if (guest != null) {
            return ResponseEntity.ok("Email Already Exist");
        }

        String currentDateTime = dateUtils.currentDateAndTime();
        Guest tempGuest = new Guest();

        tempGuest.setGuestEmail(guestRequest.getGuestEmail());
        tempGuest.setGuestName(guestRequest.getGuestName());
        tempGuest.setUpdateAt(currentDateTime);
        tempGuest.setCreateAt(currentDateTime);
        tempGuest.setGuestPhoneNumber(guestRequest.getGuestPhoneNumber());
        return new ResponseEntity<>(guestService.createGuest(tempGuest), HttpStatus.CREATED);
    }

    @GetMapping("/getAllProject")
    public ResponseEntity<?> getAllProject(@RequestParam(name = "id") Long id) {
        List<Project> gg = guestService.getAllProject(id);

        System.out.println("GGWP " + id + "\t" + gg.size());
        return new ResponseEntity<>(guestService.getAllProject(id), HttpStatus.OK);
    }

    @PostMapping("/addProject")
    public ResponseEntity<?> addProject(@RequestBody GuestProjectRequest guestProjectRequest) {
        Guest guest = guestService.checkUser(guestProjectRequest.getUserId());

        List<Long> list = guestProjectRequest.getProjectList();
        List<Project> projectList = new ArrayList<>();
        for (Long i : list) {

            Optional<Project> project = projectService.getProject(i);
            if (project.isPresent()) {
                projectList.add(project.get());
            } else {
                return ResponseEntity.ok("Project Not Found");
            }
        }

        guest.setProjects(projectList);
        guestService.createGuest(guest);


        return new ResponseEntity<>(guestService.getAllProject(guestProjectRequest.getUserId()), HttpStatus.CREATED);
    }
}
