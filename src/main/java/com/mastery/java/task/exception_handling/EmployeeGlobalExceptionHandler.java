package com.mastery.java.task.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = CustomExceptionHandler.class)
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NoSuchEmployeeException e){
        EmployeeIncorrectData data = new EmployeeIncorrectData(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

}
