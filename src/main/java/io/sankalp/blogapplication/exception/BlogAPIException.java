package io.sankalp.blogapplication.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BlogAPIException ( HttpStatus status, String message ) {
        super ( message );
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus ( ) {
        return status;
    }

    public String getMessage ( ) {
        return message;
    }
}
