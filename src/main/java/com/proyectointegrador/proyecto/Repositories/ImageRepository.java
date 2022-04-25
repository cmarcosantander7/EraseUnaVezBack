package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Image;
import com.proyectointegrador.proyecto.Models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);

}
