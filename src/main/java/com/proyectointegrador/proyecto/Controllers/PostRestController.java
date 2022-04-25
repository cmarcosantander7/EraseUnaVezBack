package com.proyectointegrador.proyecto.Controllers;

import com.proyectointegrador.proyecto.DTO.CommentRequest;
import com.proyectointegrador.proyecto.DTO.PostRequest;
import com.proyectointegrador.proyecto.DTO.PostResponse;
import com.proyectointegrador.proyecto.Service.PostService;
import com.proyectointegrador.proyecto.exception.Mensaje;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<?> responseEntity(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(new Mensaje("Cuento GUARDADO"), HttpStatus.CREATED);
    }
//
    @PutMapping
    public ResponseEntity<?> update(@RequestBody  PostRequest postRequest) {
        postService.update(postRequest);
        return new ResponseEntity(new Mensaje("Post Actualiazado"), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/comentar")
    public ResponseEntity<?> updateActividades(@PathVariable Long id, @RequestBody List<CommentRequest> comentari) {
        postService.updateComentarios(id, comentari);
        return new ResponseEntity<>(new Mensaje("Comentarios actualizados"), HttpStatus.OK);
    }

    @PutMapping("/comentario")
    public ResponseEntity<?> updateComen(@RequestBody  PostRequest postRequest) {
        postService.updateComment(postRequest);
        return new ResponseEntity(new Mensaje("Cuento Actualizado"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> listPostById(@PathVariable Long id) {
        PostResponse postResponses = postService.listPost(id);
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> listPost() {
        List<PostResponse> postResponses = postService.getAll();
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return new ResponseEntity<>(new Mensaje("Cuento Eliminado"), HttpStatus.OK);
    }
}
