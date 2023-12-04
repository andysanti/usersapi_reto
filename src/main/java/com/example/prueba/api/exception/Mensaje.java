package com.example.prueba.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Mensaje {

    private String mensaje;

    private HttpStatus status;
}
