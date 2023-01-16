package com.codex.mystore.controller;


import com.codex.mystore.constants.RoleType;
import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.user.UserDto;
import com.codex.mystore.network.request.CreateUserRequest;
import com.codex.mystore.network.request.EditUserRequest;
import com.codex.mystore.network.request.UpdatePasswordRequest;
import com.codex.mystore.models.role.Role;
import com.codex.mystore.models.user.MyUserDetails;
import com.codex.mystore.models.user.User;
import com.codex.mystore.models.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(new UserInfo(user));
    }

    @GetMapping("/secured")
    public ResponseEntity<?> getSecuredInfo() {
        return ResponseEntity.ok("Secured info");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user1 = new User(userDto);
        user1.setId(user.getId());
        //userRepository.update(user1);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return ResponseEntity.ok("Update Pasword Success");
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {

        User user = userRepository.findByEmail(createUserRequest.getEmail());

        if (user == null) {
            List<Role> roleList = new ArrayList<>();
            Role adminRole = roleRepository.findByName(RoleType.ADMIN.getRoleName());
            Role userRole = roleRepository.findByName(RoleType.USER.getRoleName());
            Role leaderRole = roleRepository.findByName(RoleType.LEADER.getRoleName());

            if (createUserRequest.getRoleList().contains(RoleType.ADMIN.getRoleType())) {
                roleList.add(adminRole);
            }
            if (createUserRequest.getRoleList().contains(RoleType.USER.getRoleType())) {
                roleList.add(userRole);
            }

            if (createUserRequest.getRoleList().contains(RoleType.LEADER.getRoleType())) {
                roleList.add(userRole);
            }

            User newUser = new User();
            newUser.setName(createUserRequest.getName());
            newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
            newUser.setEmail(createUserRequest.getEmail());
            newUser.setRoles(roleList);
            newUser.setEnabled(true);
            userRepository.save(newUser);
            return new ResponseEntity<>("Create Successfully", HttpStatus.CREATED);
        }
        return ResponseEntity.ok("User Already Exists");
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUserRequest) {
        Role adminRole = roleRepository.findByName(RoleType.ADMIN.getRoleName());
        Role userRole = roleRepository.findByName(RoleType.USER.getRoleName());
        Role leaderRole = roleRepository.findByName(RoleType.LEADER.getRoleName());
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

            if (editUserRequest.getRoleList().contains(RoleType.LEADER.getRoleType())) {
                roleList.add(leaderRole);
            }
            tempUser.setRoles(roleList);
            userRepository.save(tempUser);
            return ResponseEntity.ok("Update Success");
        }
        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
