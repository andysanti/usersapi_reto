package com.example.prueba.api.service;

import com.example.prueba.api.dto.UserDto;
import com.example.prueba.api.entity.Usuario;
import com.example.prueba.api.exception.NotUniqueEmailException;
import com.example.prueba.api.repository.TelefonoRepository;
import com.example.prueba.api.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    UserDto userDto= new UserDto();

    Usuario usuario =new Usuario();

    private static final List<Usuario> usuarios = new ArrayList<>();

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private TelefonoRepository telefonoRepository;

    @InjectMocks
    private UsuarioService usuarioService;
    private AutoCloseable autoCloseable;

    final String email = "andyprueba@domain.cl";
    final int id = 10;
    private Optional<Usuario> OpcionalUsuario = Optional.of(usuario);

    @BeforeEach
    void setUp(){
        autoCloseable=MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository,telefonoRepository);


        userDto.setId(id);
        userDto.setName("andy santi");
        userDto.setToken("");
        userDto.setActive(true);

        userDto.setEmail1(email);
        userDto.setPassword("Asddf12");

        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setToken("");
        usuario.setPassword("Asddf12");
        usuario.setName("andy santi");
        usuario.setActive(true);

        //usuarios.add(usuario);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void canGetAll() {
        //when
        usuarioService.getAll();
        //then
        Mockito.verify(usuarioRepository).findAll();
    }

    @Test
    void saveUser() throws NotUniqueEmailException {

        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(new ArrayList<>());
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        Mockito.when(usuarioRepository.findById(id)).thenReturn(OpcionalUsuario);
        assertEquals(usuarioService.saveUser(userDto).getId(),id);
    }
}