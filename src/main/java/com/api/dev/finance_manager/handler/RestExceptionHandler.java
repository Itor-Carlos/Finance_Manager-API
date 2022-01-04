package com.api.dev.finance_manager.handler;

import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundExceptionDetails;
import com.api.dev.finance_manager.exceptions.IllegalArgumentExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DespesaNotFoundException.class)
    public ResponseEntity<DespesaNotFoundExceptionDetails> despesaNotFoundException(DespesaNotFoundException despesaNotFoundException){
        DespesaNotFoundExceptionDetails despesaNotFoundDetails = new DespesaNotFoundExceptionDetails(
                "Despesa not find in this id",
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "Despesa Not Found"
        );
        return new ResponseEntity<>(despesaNotFoundDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionDetails> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        IllegalArgumentExceptionDetails illegalArgumentExceptionDetails = new IllegalArgumentExceptionDetails(
                illegalArgumentException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Illegal Argument"
        );
        return new ResponseEntity<>(illegalArgumentExceptionDetails,HttpStatus.BAD_REQUEST);
    }

}
