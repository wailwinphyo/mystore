package com.codex.mystore.dao.repo;

import com.codex.mystore.models.team.TeamPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPermissionRepository extends JpaRepository<TeamPermission, Long> {
}
