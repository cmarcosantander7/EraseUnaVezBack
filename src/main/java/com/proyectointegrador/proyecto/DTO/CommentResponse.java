package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class CommentResponse {
    private Long id;

    private String userId;
    private String userName;
    private String comentario;

    private Date createdDate;

    private Long idPost;
}
