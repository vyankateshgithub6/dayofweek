package com.finastra.spi.dayofweek.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class Handler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        if(ex instanceof DateTimeParseException){
            return new ResponseEntity<>(new Error("Invalid data format. Correct data format yyyy-MM-dd"), HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(new Error("Mandatory parameter \"date\" is missing"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}