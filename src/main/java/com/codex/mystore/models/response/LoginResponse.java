package com.codex.mystore.models.response;

import lombok.Data;

import java.util.List;


@Data
public class LoginResponse {

    private String jwtToken;
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponse(String jwt, Long id, String username, String email, List<String> roles) {
        this.jwtToken = jwt;
        this.userId = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
