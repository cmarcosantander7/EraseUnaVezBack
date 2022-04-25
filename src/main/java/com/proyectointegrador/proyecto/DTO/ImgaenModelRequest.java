package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import javax.persistence.Lob;
import java.io.Serializable;
@Data
public class ImgaenModelRequest implements Serializable {
    private Long id;
    private String name;

    private String type;

    @Lob
    private byte[] image;
}
