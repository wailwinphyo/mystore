package com.codex.mystore.dao.repo;

import com.codex.mystore.models.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {
    
}
