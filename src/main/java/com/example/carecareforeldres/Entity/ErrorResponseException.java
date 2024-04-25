package com.example.carecareforeldres.Entity;
import org.springframework.http.HttpStatus;

public class ErrorResponseException extends RuntimeException {
    private HttpStatus status;
    private String message;
    private Throwable cause;

    public ErrorResponseException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }




    public ErrorResponseException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = status;
        this.cause = cause;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}