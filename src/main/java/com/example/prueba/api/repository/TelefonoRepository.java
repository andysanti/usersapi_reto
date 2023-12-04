package com.example.prueba.api.repository;

import com.example.prueba.api.entity.Telefono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefonoRepository extends CrudRepository<Telefono,Long> {

    List<Telefono> findById(Integer numero);

}
