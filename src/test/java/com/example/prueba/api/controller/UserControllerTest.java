package com.example.prueba.api.controller;

import com.example.prueba.api.dto.UserDto;
import com.example.prueba.api.exception.NotUniqueEmailException;
import com.example.prueba.api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void save() throws Exception {

        UserDto newUser = new UserDto();
        newUser.setName("andy santi");
        newUser.setEmail1("Adcvf45");
        newUser.setActive(true);
        newUser.setId(10);
        newUser.setToken("ABCD123");

        Mockito.when(usuarioService.saveUser(Mockito.any(UserDto.class))).thenReturn(newUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                .content(new ObjectMapper().writeValueAsString(newUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getAll() {
    }
}