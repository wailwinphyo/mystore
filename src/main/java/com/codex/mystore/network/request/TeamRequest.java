package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TeamRequest {
    private String teamName;
    private ArrayList<Integer> memberList;

}
