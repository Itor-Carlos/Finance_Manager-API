package com.api.dev.finance_manager.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DespesaFieldNotValidExceptionDetails extends ExceptionDetails{

    private Map<String,String> messages;

    //CONSTRUCTOR
    public DespesaFieldNotValidExceptionDetails(Integer httpStatus, LocalDateTime timeStamp, String exception, Map<String,String> messages) {
        super(httpStatus, timeStamp, exception);
        this.messages = messages;
    }

    //GETTERS AND SETTERS
    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
    
}
