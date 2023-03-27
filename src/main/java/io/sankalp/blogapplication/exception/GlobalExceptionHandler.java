package io.sankalp.blogapplication.exception;

import io.sankalp.blogapplication.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler ( ResourceNotFoundException.class )
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException ( ResourceNotFoundException exception,
                                                                          WebRequest request ) {
          ErrorDetails errorDetails = new ErrorDetails ( new Date(),
                                                        exception.getMessage (),
                                                        request.getDescription ( false ) );

          return new ResponseEntity<> ( errorDetails, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler ( BlogAPIException.class )
    public ResponseEntity<ErrorDetails> handleBlogAPIException ( BlogAPIException exception,
                                                                 WebRequest request ) {
        ErrorDetails errorDetails = new ErrorDetails ( new Date (),
                                                       exception.getMessage (),
                                                       request.getDescription ( false ) );

        return new ResponseEntity<> ( errorDetails, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler ( Exception.class )
    public ResponseEntity<ErrorDetails> handleGenericException ( Exception exception,
                                                                 WebRequest request ) {
        ErrorDetails errorDetails = new ErrorDetails ( new Date (),
                                                       exception.getMessage (),
                                                       request.getDescription ( false ) );

        return new ResponseEntity<> ( errorDetails, HttpStatus.INTERNAL_SERVER_ERROR );
    }

}
