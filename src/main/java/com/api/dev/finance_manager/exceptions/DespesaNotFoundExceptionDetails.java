package com.api.dev.finance_manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DespesaNotFoundExceptionDetails extends ExceptionDetails{

    private String message;

    public DespesaNotFoundExceptionDetails(int httpStatus, LocalDateTime timeStamp, String exception,String message) {
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
