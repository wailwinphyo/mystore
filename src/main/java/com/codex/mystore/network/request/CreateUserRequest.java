package com.codex.mystore.network.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    private String name;

    private String email;

    private String password;

    List<Integer> roleList;
}
