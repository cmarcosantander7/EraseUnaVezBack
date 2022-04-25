package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}