package com.proyectointegrador.proyecto.Service;

import com.proyectointegrador.proyecto.DTO.FechaResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
    public class FechaService {

        public FechaResponse obtenerHoraFecha(){
            FechaResponse f= new FechaResponse();
            f.setFecha(LocalDateTime.now());
            return f;
        }

}
