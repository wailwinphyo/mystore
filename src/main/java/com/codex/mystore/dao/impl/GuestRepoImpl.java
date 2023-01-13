package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.GuestRepo;
import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
