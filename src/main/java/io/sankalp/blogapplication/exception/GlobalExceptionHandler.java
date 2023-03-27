package io.sankalp.blogapplication.exception;

import io.sankalp.blogapplication.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException exception,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult ().getAllErrors ().forEach ( ( error ) -> {
            String fieldName = ( ( FieldError ) error ).getField ();
            String message = error.getDefaultMessage ();
            errors.put ( fieldName, message );
        } );

        return new ResponseEntity<> ( errors, HttpStatus.BAD_REQUEST );
    }

    /*
    @ExceptionHandler ( MethodArgumentNotValidException.class )
    public ResponseEntity<Object> handleMethodArgumentNotValidException ( MethodArgumentNotValidException exception,
                                                                          WebRequest request ) {
        Map<String, String> errors = new HashMap<> ();

        exception.getBindingResult ().getAllErrors ().forEach ( ( error ) -> {
            String fieldName = ( ( FieldError ) error ).getField ();
            String message = error.getDefaultMessage ();
            errors.put ( fieldName, message );
        });

        return new ResponseEntity<> ( errors, HttpStatus.BAD_REQUEST );
    }
    */
}
