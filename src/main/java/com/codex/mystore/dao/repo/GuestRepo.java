package com.codex.mystore.dao.repo;

import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.models.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<Guest, Long> {
    Guest findByGuestEmail(String mail);
}
