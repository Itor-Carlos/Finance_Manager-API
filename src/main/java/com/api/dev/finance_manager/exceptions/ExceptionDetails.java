package com.api.dev.finance_manager.exceptions;

import java.time.LocalDateTime;

public class ExceptionDetails {
    private int httpStatus;
    private LocalDateTime timeStamp;
    private String exception;

    public ExceptionDetails(int httpStatus, LocalDateTime timeStamp, String exception) {
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.exception = exception;
    }
    
    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
