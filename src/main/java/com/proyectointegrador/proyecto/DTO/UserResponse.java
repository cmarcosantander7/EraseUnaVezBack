package com.proyectointegrador.proyecto.DTO;

import lombok.*;

import java.io.Serializable;

//GENERAL--> SE COMUNICA CON EL MODELO
@Getter
@Setter
@Data
@NoArgsConstructor

public class UserResponse implements Serializable {

    private String id;

    private String email;

    private String clave;

    private String token;

    private String username;


    public UserResponse(String id, String email, String clave) {
        this.id = id;
        this.email = email;
        this.clave = clave;
    }

    public UserResponse(String id, String email, String clave,  String token, String username) {
        this.id = id;
        this.email = email;
        this.clave = clave;

        this.token = token;
        this.username = username;
    }

    public UserResponse(String id, String email, String clave, String username) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.username = username;
    }

}



