package com.codex.mystore.controller.auth;

import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.models.request.RegisterRequest;
import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.user.User;
import com.codex.mystore.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    RoleRepository roleRepository;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequest registerRequest) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        if (registerRequest.getRoleList().contains(1)) {
            roleList.add(adminRole);
        }
        if (registerRequest.getRoleList().contains(2)) {
            roleList.add(userRole);
        }

        User user = new User();
        user.setPassword(registerRequest.getUserPassword());
        user.setEmail(registerRequest.getUserEmail());
        user.setEnabled(true);
        user.setRoles(roleList);
        return new ResponseEntity<User>(registerService.createUser(user), HttpStatus.CREATED);
    }

}
