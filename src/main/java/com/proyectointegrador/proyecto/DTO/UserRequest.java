package com.proyectointegrador.proyecto.DTO;

import lombok.Data;

import java.io.Serializable;

//Si existe cambio
@Data
public class UserRequest implements Serializable {

    private String email;
    private String clave;
    private String username;

    public UserRequest() {
    }

    public UserRequest(String email, String clave, String username) {
        this.email = email;
        this.clave = clave;
        this.username=username;
    }




}
