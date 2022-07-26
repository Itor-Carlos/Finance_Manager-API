package com.api.dev.finance_manager.handler;

import com.api.dev.finance_manager.exceptions.DespesaFieldNotValidExceptionDetails;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundExceptionDetails;
import com.api.dev.finance_manager.exceptions.IllegalArgumentExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "com.api.dev.finance_manager.controllers")
public class RestExceptionHandler {

    @ExceptionHandler(DespesaNotFoundException.class)
    public ResponseEntity<DespesaNotFoundExceptionDetails> despesaNotFoundException(DespesaNotFoundException despesaNotFoundException){
        DespesaNotFoundExceptionDetails despesaNotFoundDetails = new DespesaNotFoundExceptionDetails(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "Despesa Not Found",
                "Despesa not find in this id"
        );
        return new ResponseEntity<>(despesaNotFoundDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionDetails> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        IllegalArgumentExceptionDetails illegalArgumentExceptionDetails = new IllegalArgumentExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Illegal Argument",
                illegalArgumentException.getMessage()
        );
        return new ResponseEntity<>(illegalArgumentExceptionDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DespesaFieldNotValidExceptionDetails> despesaFieldNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> fieldMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());//get the message of this errors
        List<String> fieldError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.toList());;//get the name of field of this erros

        Map<String,String> messageErrors = new HashMap<>();

        for(int i = 0; i<fieldErrorList.size();i++){
            messageErrors.put(fieldError.get(i), fieldMessage.get(i));
        }

        DespesaFieldNotValidExceptionDetails despesaFieldNotValidExceptionDetails = new DespesaFieldNotValidExceptionDetails(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "DespesaFieldNotValidExceptionDetails", messageErrors);
        
        return new ResponseEntity<>(despesaFieldNotValidExceptionDetails,HttpStatus.BAD_REQUEST);
    }

}
