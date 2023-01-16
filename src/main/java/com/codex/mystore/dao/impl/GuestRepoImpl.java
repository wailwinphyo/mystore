package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.GuestRepo;
import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.models.project.Project;
import com.codex.mystore.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("guestService")
public class GuestRepoImpl implements GuestService {
    @Autowired
    GuestRepo guestRepo;

    @Override
    public Guest findByEmail(String email) {
        return guestRepo.findByGuestEmail(email);
    }

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepo.save(guest);
    }

    @Override
    public List<Project> getAllProject(Long id) {
        Optional<Guest> guest = guestRepo.findById(id);
        if (guest.isPresent()) {
            Guest tempGuest = guest.get();
            return new ArrayList<>(tempGuest.getProjects());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Guest checkUser(Long id) {
        Optional<Guest> guest = guestRepo.findById(id);
        return guest.orElse(new Guest());

    }
}
