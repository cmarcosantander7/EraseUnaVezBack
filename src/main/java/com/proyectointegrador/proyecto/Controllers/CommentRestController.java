package com.proyectointegrador.proyecto.Controllers;


import com.proyectointegrador.proyecto.DTO.CommentRequest;
import com.proyectointegrador.proyecto.DTO.CommentResponse;
import com.proyectointegrador.proyecto.Service.CommentService;
import com.proyectointegrador.proyecto.exception.Mensaje;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CommentRequest commentRequest){
        commentService.save(commentRequest);
        return new ResponseEntity(new Mensaje("Comentario creado"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CommentRequest commentRequest) {
        commentService.update(commentRequest);
        return new ResponseEntity(new Mensaje("Comentario Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/allByPost/{idPost}")
    public ResponseEntity<List<CommentResponse>> listCommentsPost(@PathVariable Long idPost) {
        List<CommentResponse> comment = commentService.listComentarioIdPost(idPost);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentResponse>> listComment() {
        List<CommentResponse> comment = commentService.getAll();
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
