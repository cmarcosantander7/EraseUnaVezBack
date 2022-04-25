package com.proyectointegrador.proyecto.DTO;

import com.proyectointegrador.proyecto.Models.Post;
import lombok.Data;

import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
public class SubredditResponse {
    private Long id;

    private String userId;

    private String nombre;

    private String descripcion;

    private Date createdDate;
}
