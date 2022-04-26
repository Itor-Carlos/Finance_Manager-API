package com.api.dev.finance_manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentExceptionDetails extends ExceptionDetails{

    private String message;

    public IllegalArgumentExceptionDetails(int httpStatus, LocalDateTime timeStamp, String exception,String message) {
        super(httpStatus, timeStamp, exception);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
