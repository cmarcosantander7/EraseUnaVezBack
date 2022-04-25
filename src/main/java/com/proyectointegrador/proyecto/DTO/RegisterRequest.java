package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {

    private String email;

    private String clave;
    private String username;

    public RegisterRequest(String email,  String clave, String username) {
        this.email = email;
        this.clave = clave;
        this.username = username;

    }

    public RegisterRequest() {
    }


}
