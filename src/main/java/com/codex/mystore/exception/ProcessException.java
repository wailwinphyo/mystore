package com.codex.mystore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ProcessException extends RuntimeException {
    String reason;

    public ProcessException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
