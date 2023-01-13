package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ProjectRequest {
    private String projectName;
    private String projectDescription;
    private String createAt;
    private String updateAt;
    private ArrayList<Integer> memberList;
}
