package com.example.prueba.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomerException  extends ResponseEntityExceptionHandler {

    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

    @ExceptionHandler({NotUniqueEmailException.class})
    public ResponseEntity<Object> handleNotUniqueEmailException(NotUniqueEmailException ex){

        Mensaje mensaje = new Mensaje(ex.getMessage(), HttpStatus.CONFLICT);
        return buildResponse(mensaje);
    }
    private ResponseEntity<Object> buildResponse(Mensaje mensaje){
        log.error("Error status={} mensaje={}" ,mensaje.getStatus(),mensaje.getMensaje());
        return new ResponseEntity<>(mensaje,mensaje.getStatus());
    }

}


