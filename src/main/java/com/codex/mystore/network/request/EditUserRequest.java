package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EditUserRequest {
    Long id;
    ArrayList<Integer> roleList;
}
