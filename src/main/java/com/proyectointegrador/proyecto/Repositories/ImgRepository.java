package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImgRepository  extends JpaRepository<Image, Long> {
        Optional<Image> findByName(String name);

}
