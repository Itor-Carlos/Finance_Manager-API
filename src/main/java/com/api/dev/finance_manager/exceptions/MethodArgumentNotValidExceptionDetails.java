package com.api.dev.finance_manager.exceptions;

import java.time.LocalDateTime;

public class MethodArgumentNotValidExceptionDetails extends ExceptionDetails{
    private String fieldError;

    public MethodArgumentNotValidExceptionDetails(String message, int httpStatus, LocalDateTime timeStamp, String exception, String fieldError) {
        super(message, httpStatus, timeStamp, exception);
        this.fieldError = fieldError;
    }

    public String getFieldError() {
        return fieldError;
    }

    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
    }
}
