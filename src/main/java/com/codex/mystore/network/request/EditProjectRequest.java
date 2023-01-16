package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EditProjectRequest {

    private Long projectId;
    private String projectName;
    private String projectDescription;
    private ArrayList<Integer> memberList;
}

