package com.example.prueba.api.controller;

import com.example.prueba.api.dto.UserDto;
import com.example.prueba.api.exception.NotUniqueEmailException;
import com.example.prueba.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsuarioService usuarioService;

    @Operation(summary = "save User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "save User"
            ,content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "409",description = "error ,no cumple validaciones",
            content = @Content)
    })
    @PostMapping(value = "/" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody @Valid UserDto userDto) throws NotUniqueEmailException {

        return new ResponseEntity<>(usuarioService.saveUser(userDto), HttpStatus.CREATED);

    }

    @GetMapping(value = "/" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getAll(){

        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);

    }


}
