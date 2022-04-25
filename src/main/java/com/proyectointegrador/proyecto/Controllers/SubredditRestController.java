package com.proyectointegrador.proyecto.Controllers;




import com.proyectointegrador.proyecto.DTO.SubredditRequest;
import com.proyectointegrador.proyecto.DTO.SubredditResponse;
import com.proyectointegrador.proyecto.Service.SubredditService;
import com.proyectointegrador.proyecto.exception.Mensaje;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
public class SubredditRestController {

    private final SubredditService subredditService;

    @PostMapping("/save")
    public ResponseEntity<?> responseEntity(@RequestBody SubredditRequest subredditRequest) {
        subredditService.save(subredditRequest);
        return new ResponseEntity<>(new Mensaje("Subreddit GUARDADO"), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody  SubredditRequest subredditRequest) {
        subredditService.update(subredditRequest);
        return new ResponseEntity(new Mensaje("Subreddit Actualiazado"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditResponse> listSubredditById(@PathVariable Long id) {
        SubredditResponse subredditList = subredditService.listSubreddit(id);
        return new ResponseEntity<>(subredditList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubredditResponse>> listSubreddit() {
        List<SubredditResponse> subredditList = subredditService.getAll();
        return new ResponseEntity<>(subredditList, HttpStatus.OK);
    }
}
