package com.codex.mystore.models.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RegisterRequest {
    String userName;
    String userEmail;
    String userPassword;
    ArrayList<Integer> roleList;
}
