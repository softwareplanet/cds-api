package com.interlink.cds.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private  HttpStatus httpStatus;
    private String error;
    private int status;
    private String statusText;

    public HttpException(HttpStatus status, String error ) {
        super(error);
        this.httpStatus = status;
        this.statusText = status.getReasonPhrase();
        this.status = status.value();
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
