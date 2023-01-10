package com.codex.mystore.dao.repo;

import com.codex.mystore.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    public Role findByName(String name);
}
