package com.mastery.java.task.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = CustomExceptionHandler.class)
public class EmployeeGlobalExceptionHandler {

    // default exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerError> handleException(
            Exception e){
        ServerError data = new ServerError(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NoSuchEmployeeException e){
        EmployeeIncorrectData data = new EmployeeIncorrectData(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeIncorrectData> handleExceptionValidAge(
            MethodArgumentNotValidException e){
        EmployeeIncorrectData data = new EmployeeIncorrectData(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
