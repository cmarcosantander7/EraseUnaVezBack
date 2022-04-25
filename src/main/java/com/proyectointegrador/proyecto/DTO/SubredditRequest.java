package com.proyectointegrador.proyecto.DTO;
import lombok.Data;
import java.util.Date;

@Data
public class SubredditRequest {
    private Long id;

    private String userId;

    private String nombre;

    private String descripcion;

    private Date createdDate;
}
