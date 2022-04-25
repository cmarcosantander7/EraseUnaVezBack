package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.Comment;
import com.proyectointegrador.proyecto.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    List<Comment> findAllByUserId(String userId);

    Optional<Comment> findById(Long id);
}
