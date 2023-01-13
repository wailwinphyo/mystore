package com.codex.mystore.controller.role;

import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.network.request.RoleRequest;
import com.codex.mystore.models.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PostMapping(value = "/createRole")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleRepository.findByName(roleRequest.getRoleName());
        System.out.println(role);

        if (role != null && role.getName().equals(role.getName())) {
            return ResponseEntity.ok("Role Already Exist");
        }

        roleRepository.save(new Role(roleRequest.getRoleName()));
        return ResponseEntity.ok("Update Role Success");
    }
}
