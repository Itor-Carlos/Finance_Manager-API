package com.api.dev.finance_manager.handler;

import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundExceptionDetails;
import com.api.dev.finance_manager.exceptions.IllegalArgumentExceptionDetails;
import com.api.dev.finance_manager.exceptions.MethodArgumentNotValidExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
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
    public ResponseEntity<MethodArgumentNotValidExceptionDetails> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fieldMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining());
        String fieldError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining());

        MethodArgumentNotValidExceptionDetails methodArgumentException = new MethodArgumentNotValidExceptionDetails(
                fieldMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Method Argument Not Valid Exception",
                fieldError
        );
        return new ResponseEntity<>(methodArgumentException,HttpStatus.BAD_REQUEST);
    }

}
