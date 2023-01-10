package com.codex.mystore.models.request;

import lombok.Data;

@Data
public class LoginRequest {

    public String username;

    public String password;
}
