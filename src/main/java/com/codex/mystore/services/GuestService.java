package com.codex.mystore.services;

import com.codex.mystore.models.guest.Guest;

public interface GuestService {
    Guest findByEmail(String email);

    Guest createGuest(Guest guest);
}
