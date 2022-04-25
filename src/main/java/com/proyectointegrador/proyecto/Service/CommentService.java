package com.proyectointegrador.proyecto.Service;

import com.proyectointegrador.proyecto.DTO.CommentRequest;
import com.proyectointegrador.proyecto.DTO.CommentResponse;
import com.proyectointegrador.proyecto.Models.Comment;
import com.proyectointegrador.proyecto.Models.Post;
import com.proyectointegrador.proyecto.Repositories.CommentRepository;
import com.proyectointegrador.proyecto.Repositories.PostRepository;
import com.proyectointegrador.proyecto.exception.BadRequestException;
import com.proyectointegrador.proyecto.exception.ResponseNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private final CommentRepository commentRepository;

    public void save(CommentRequest request){
        Optional<Post> post = postRepository.findById(request.getIdPost());
        if(post.isPresent()){
            Comment comentario = new Comment();
            comentario.setUserId(request.getUserId());
            comentario.setUserName(request.getUserName());
            comentario.setComentario(request.getComentario());
            comentario.setCreatedDate(request.getCreatedDate());
            comentario.setPost(postRepository.findById(request.getIdPost()).orElseThrow(() -> new
                    ResponseNotFoundException("POST", "ID", request.getIdPost() + "")));
            try{
                commentRepository.save(comentario);
            }catch(Exception e){
                throw new BadRequestException("No se guardó el comentario " + e);
            }
        }
        throw  new BadRequestException("No existe el post con id: "+request.getIdPost());

    }

    public Boolean update(CommentRequest request){
        Optional<Comment> optional= commentRepository.findById(request.getId());
        if(optional.isPresent()){
            optional.get().setComentario(request.getComentario());
            try{
                Comment c= commentRepository.save(optional.get());
                return true;

            }catch(Exception ex){
                throw new BadRequestException("No se actualizó el comentario" + ex);
            }
        }
        throw new ResponseNotFoundException("Comentario", "ID:", "" + request.getId());
    }


    public List<CommentResponse> listComentarioIdPost(Long id){
        Optional<Post> op=postRepository.findById(id);
        if(op.isPresent()){
            List<Comment> lista=commentRepository.findAllByPost(op.get());
            return lista.stream().map(comment -> {
                CommentResponse cr= new CommentResponse();
                cr.setId(comment.getId());
                cr.setUserId(comment.getUserId());
                cr.setComentario(comment.getComentario());
                cr.setCreatedDate(comment.getCreatedDate());
                cr.setUserId(comment.getUserId());
                cr.setUserName(comment.getUserName());
                return cr;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException("No existe el post");
    }

    public List<CommentResponse> getAll(){
        List<Comment> lista = commentRepository.findAll();
        return lista.stream().map(comment -> {
            CommentResponse cr= new CommentResponse();
            cr.setId(comment.getId());
            cr.setUserId(comment.getUserId());
            cr.setComentario(comment.getComentario());
            cr.setCreatedDate(comment.getCreatedDate());
            cr.setUserId(comment.getUserId());
            cr.setUserName(comment.getUserName());
            return cr;
        }).collect(Collectors.toList());
    }
}
