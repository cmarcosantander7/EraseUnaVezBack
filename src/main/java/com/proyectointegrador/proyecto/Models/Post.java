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

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userName;
    private String postName;
    @Column(length = 10485760)
    private String url;
    @Column(length = 10485760)
    private String descripcion;

    private Integer voteCount=0;
    private byte[] imagen;
    private Date createdDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "subreddit_id", referencedColumnName = "id")
    private Subreddit subreddit;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToMany(targetEntity = Comment.class, mappedBy = "post")
    private List<Comment> comment;



}
