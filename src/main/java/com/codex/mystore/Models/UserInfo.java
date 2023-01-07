package com.codex.mystore.Models;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserInfo {

    private String name;

    private String email;

    private List<String> authorities;


    public UserInfo(MyUserDetails user) {
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.authorities = user.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList());
    }
}
