package com.mastery.java.task.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class EmployeeGlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(EmployeeGlobalExceptionHandler.class);

    @ExceptionHandler(MyServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchEmployeeException(MyServiceNotFoundException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e){
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

//     обработать все ошибки на сервере
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e){
        logger.error(e.getMessage(), e);
        return "Something went wrong";
    }
}
