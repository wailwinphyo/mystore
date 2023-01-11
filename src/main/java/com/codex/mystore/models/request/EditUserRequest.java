package com.codex.mystore.models.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EditUserRequest {
    Long id;
    ArrayList<Integer> roleList;
}
