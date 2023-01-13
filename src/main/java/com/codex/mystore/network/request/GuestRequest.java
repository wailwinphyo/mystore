package com.codex.mystore.network.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GuestRequest {
    String guestName;
    String guestEmail;
    String guestPhoneNumber;
    String createAt;
    String updateAt;
    ArrayList<Integer> projectId;
}
