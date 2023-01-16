package com.codex.mystore.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class DateUtils {
    public String currentDateAndTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
