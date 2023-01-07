package com.codex.mystore.Controller;


import com.codex.mystore.Models.MyUserDetails;
import com.codex.mystore.Models.User;
import com.codex.mystore.Models.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(){
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new UserInfo(user));
    }

    @GetMapping("/secured")

    public ResponseEntity<?> getSecuredInfo(){
        return ResponseEntity.ok("Secured info");
    }
}
