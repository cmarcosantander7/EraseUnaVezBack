package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario,String> {

    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
}
