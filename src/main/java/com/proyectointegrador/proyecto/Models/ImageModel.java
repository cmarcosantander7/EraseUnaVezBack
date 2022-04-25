package com.proyectointegrador.proyecto.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
    @Table(name="image_model")

    @Data
    @NoArgsConstructor
    @ToString
    public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    public ImageModel(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }
    public ImageModel(Long id,String name, String type, byte[] pic) {
        this.id=id;
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

}