package com.codex.mystore.controller;


import com.codex.mystore.constants.RoleType;
import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.exception.ProcessException;
import com.codex.mystore.models.request.EditUserRequest;
import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.user.MyUserDetails;
import com.codex.mystore.models.user.User;
import com.codex.mystore.models.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new UserInfo(user));
    }

    @GetMapping("/secured")
    public ResponseEntity<?> getSecuredInfo() {
        return ResponseEntity.ok("Secured info");
    }

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUserRequest) {


        Role adminRole = roleRepository.findByName(RoleType.ADMIN.getRoleName());
        Role userRole = roleRepository.findByName(RoleType.USER.getRoleName());
        Optional<User> user = userRepository.findById(editUserRequest.getId());
        if (user.isPresent()) {
            User tempUser = user.get();
            List<Role> roleList = new ArrayList<>();
            if (editUserRequest.getRoleList().contains(RoleType.ADMIN.getRoleType())) {
                roleList.add(adminRole);
            }
            if (editUserRequest.getRoleList().contains(RoleType.USER.getRoleType())) {
                roleList.add(userRole);
            }
            tempUser.setRoles(roleList);
            userRepository.save(tempUser);
            return ResponseEntity.ok("Update Success");
        }
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
