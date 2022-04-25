package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
    Optional<Subreddit> findByNombre(String subredditName);

}
