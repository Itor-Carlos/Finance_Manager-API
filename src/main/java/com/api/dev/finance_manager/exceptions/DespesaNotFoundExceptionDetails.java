package com.api.dev.finance_manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DespesaNotFoundExceptionDetails extends ExceptionDetails{
    public DespesaNotFoundExceptionDetails(String message, int httpStatus, LocalDateTime timeStamp, String exception) {
        super(message, httpStatus, timeStamp, exception);
    }
}
