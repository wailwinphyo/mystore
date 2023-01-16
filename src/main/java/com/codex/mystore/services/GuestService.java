package com.codex.mystore.services;

import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.models.project.Project;

import java.util.List;

public interface GuestService {
    Guest findByEmail(String email);

    Guest createGuest(Guest guest);

    List<Project> getAllProject(Long id);

    Guest checkUser(Long id);


}
