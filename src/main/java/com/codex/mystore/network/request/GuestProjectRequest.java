package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GuestProjectRequest {
    private Long userId;
    private ArrayList<Long> projectList;
}
