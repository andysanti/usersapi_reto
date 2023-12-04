package com.example.prueba.api.dto;

import com.example.prueba.api.entity.Usuario;
import com.example.prueba.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data

public class UserDto {


    private String mensaje;

    private Integer id;

    private String name;

    private String email;

    private String password;

    private String token;

    private boolean active;

    private LocalDateTime created;

    private LocalDateTime modified;

    private LocalDateTime last_login;

    private List<TelefonoDto> phones;

    public static UserDto toDto(Usuario usuario){

        UserDto user = new UserDto();
        user.setId(usuario.getId());
        user.setEmail(usuario.getEmail());
        user.setName(usuario.getName());
        user.setActive(usuario.isActive());
        user.setCreated(usuario.getCreated());
        user.setModified(usuario.getModified());
        user.setLast_login(usuario.getLast_login());
        user.setToken(usuario.getToken());

        //user.setPhones(usuario.getTelefonos().stream().map(UsuarioService::getTelefono).collect(Collectors.toList()));

        return user;

    }
    @SneakyThrows
    public String toString() {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);

    }
}
