package com.proyectointegrador.proyecto.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Table(name = "subreddit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subreddit implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String nombre;

    private String descripcion;

    @OneToMany(targetEntity = Post.class, mappedBy = "subreddit")
    private List<Post> posts;

    private Date createdDate;
}
