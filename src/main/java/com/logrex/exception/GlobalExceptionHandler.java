package com.logrex.exception;

import com.logrex.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundExceptions.class)
    ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            NotFoundExceptions exceptions, WebRequest webRequest
            ){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exceptions.getMessage(),
                webRequest.getDescription(false));

 return  new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDetails> badhandlResourceNotFoundException(
            Exception exceptions, WebRequest webRequest
    ){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exceptions.getMessage(),
                webRequest.getDescription(false));

        return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

       Map<String,String> errors= new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error)->{

           String fieldName=((FieldError) error).getField();
           String message=error.getDefaultMessage();
           System.out.println("Field name :"+fieldName);
           errors.put(fieldName,message);
       });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
