package com.codex.mystore.Dao;

import com.codex.mystore.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    public Role findByName(String name);
}
