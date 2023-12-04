package com.example.prueba.api.repository;

import com.example.prueba.api.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario,Integer> {

    List<Usuario> findByEmail(String email);

}
