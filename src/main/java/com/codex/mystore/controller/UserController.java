package com.codex.mystore.controller;


import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.user.MyUserDetails;
import com.codex.mystore.models.user.User;
import com.codex.mystore.models.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

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
}
