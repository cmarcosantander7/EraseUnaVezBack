package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PostRequest implements Serializable {

    private Long id;

    private String userId;

    private String postName;
    private String userName;

    private String url;

    private String descripcion;

    private Integer voteCount;

    private Date createdDate;

    private Long idSubreddit;
    private Long idimage;
    private byte[] imagen;

    private List<CommentRequest> comentarios;



   // private List<VoteRequest> vote;
}
