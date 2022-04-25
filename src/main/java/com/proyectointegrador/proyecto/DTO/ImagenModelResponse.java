package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class ImagenModelResponse implements Serializable {

    private Long id;
    private String name;

    private String type;
    @Lob
    private byte[] image;
}
