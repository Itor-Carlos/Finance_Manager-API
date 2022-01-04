package com.api.dev.finance_manager.handler;

import com.api.dev.finance_manager.exceptions.DespesaNotFoundException;
import com.api.dev.finance_manager.exceptions.DespesaNotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

}
