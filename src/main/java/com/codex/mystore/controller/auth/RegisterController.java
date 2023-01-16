package com.codex.mystore.controller.auth;

import com.codex.mystore.constants.RoleType;
import com.codex.mystore.dao.repo.RoleRepository;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.exception.ProcessException;
import com.codex.mystore.network.request.RegisterRequest;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequest registerRequest) {
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(registerRequest.getUserEmail());

        if (!matcher.matches()) {
            throw new ProcessException("Invalid Email Format");
        }
        if (userRepository.findByEmail(registerRequest.getUserEmail()) != null) {
            throw new ProcessException("Email Already Exist");
        }

        Role adminRole = roleRepository.findByName(RoleType.ADMIN.getRoleName());
        Role userRole = roleRepository.findByName(RoleType.USER.getRoleName());


        List<Role> roleList = new ArrayList<>();
        if (registerRequest.getRoleList().contains(RoleType.ADMIN.getRoleType())) {
            roleList.add(adminRole);
        }
        if (registerRequest.getRoleList().contains(RoleType.USER.getRoleType())) {
            roleList.add(userRole);
        }

        User user = new User();
        user.setPassword(registerRequest.getUserPassword());
        user.setEmail(registerRequest.getUserEmail());
        user.setEnabled(true);
        user.setRoles(roleList);
        return new ResponseEntity<>(registerService.createUser(user), HttpStatus.CREATED);
    }

}
