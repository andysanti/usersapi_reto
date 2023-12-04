package com.example.prueba.api.repository;

import com.example.prueba.api.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void exitsFindByEmail(){

        String email = "andysanti23@dominio.cl";

       List<Usuario> list= usuarioRepository.findByEmail(email);
       assertEquals(1,list.size());
    }
}