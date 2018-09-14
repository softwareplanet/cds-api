package com.interlink.cds.exception.exceptionControllers;

import com.interlink.cds.entities.exceptionEntity.ApiError;
import com.interlink.cds.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity notFoundException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity notFoundField(HttpException e){
            ApiError apiError = new ApiError(e.getHttpStatus(), e.getError());
        return new ResponseEntity<>(apiError, e.getHttpStatus());
    }
}
