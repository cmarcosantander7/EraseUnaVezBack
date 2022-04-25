package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Post;
import com.proyectointegrador.proyecto.Models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUserId(String user);
}
