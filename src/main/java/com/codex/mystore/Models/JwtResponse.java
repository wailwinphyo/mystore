package com.codex.mystore.Models;

import lombok.Data;

import java.util.List;


@Data
public class JwtResponse{

    private String jwtToken;
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String jwt, Long id, String username, String email, List<String> roles) {
        this.jwtToken = jwt;
        this.userId = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
