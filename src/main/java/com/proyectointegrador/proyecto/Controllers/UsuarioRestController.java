package com.proyectointegrador.proyecto.Controllers;

import com.proyectointegrador.proyecto.DTO.RegisterRequest;
import com.proyectointegrador.proyecto.DTO.UserRequest;
import com.proyectointegrador.proyecto.DTO.UserResponse;
import com.proyectointegrador.proyecto.Service.AuthService;
import com.proyectointegrador.proyecto.exception.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"https://cmarcosantander7.github.io/EraseUnaVezFront"})
@RestController
@RequestMapping("/api/auth")
public class UsuarioRestController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest) throws Exception {
        UserResponse userResponse = authService.login(userRequest);
        if (userResponse == null){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
        }

    }



    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody RegisterRequest userRequest) throws Exception {
        UserResponse userResponse = authService.signup(userRequest);

        if (userResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        UserResponse userResponse=authService.getUser(email);
        if (userResponse == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


}
