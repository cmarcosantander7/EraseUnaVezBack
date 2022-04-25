package com.proyectointegrador.proyecto.Service;

import com.proyectointegrador.proyecto.DTO.*;
import com.proyectointegrador.proyecto.Models.Comment;
import com.proyectointegrador.proyecto.Models.Image;
import com.proyectointegrador.proyecto.Models.Post;
import com.proyectointegrador.proyecto.Repositories.*;
import com.proyectointegrador.proyecto.exception.BadRequestException;
import com.proyectointegrador.proyecto.exception.ResponseNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private ImgRepository imgRepository;
    @Autowired
    private CommentRepository commentRepository;

    public void save(PostRequest request){
        Post post = new Post();
        post.setUserId(request.getUserId());
        post.setPostName(request.getPostName());
        post.setUserName(request.getUserName());
        post.setUrl(request.getUrl());
        post.setDescripcion(request.getDescripcion());
        post.setVoteCount(request.getVoteCount());
        post.setCreatedDate(request.getCreatedDate());
        post.setImagen(request.getImagen());
        post.setSubreddit(subredditRepository.findById(request.getIdSubreddit()).orElseThrow(() -> new
               ResponseNotFoundException("SUBREDDIT", "ID", request.getIdSubreddit() + "")));
        post.setImage(imgRepository.findById(request.getIdimage()).orElseThrow(() -> new
                ResponseNotFoundException("IMAGEN", "ID", request.getIdimage() + "")));
        try{
            Post a = postRepository.save(post);

        }catch(Exception e){
            throw new BadRequestException("No se guardó el post " + e);
        }
    }


    public Boolean update(PostRequest request){
        Optional<Post> optional= postRepository.findById(request.getId());
        if(optional.isPresent()){
            optional.get().setPostName(request.getPostName());
           optional.get().setUserName(request.getUserName());
            optional.get().setUrl(request.getUrl());
            optional.get().setDescripcion(request.getDescripcion());
            optional.get().setVoteCount(request.getVoteCount());
            optional.get().setSubreddit(subredditRepository.findById(request.getIdSubreddit()).orElseThrow(() -> new
                    ResponseNotFoundException("SUBREDDIT", "ID", request.getIdSubreddit() + "")));

            try{
                Post c= postRepository.save(optional.get());
                return true;

            }catch(Exception ex){
                throw new BadRequestException("No se actualizó el post" + ex);
            }
        }
        throw new ResponseNotFoundException("POST", "ID:", "" + request.getId());
    }

    public Boolean updateComment(PostRequest request){
       Post post = new Post();
        post.setPostName(request.getPostName());
        post.setUrl(request.getUrl());
        post.setDescripcion(request.getDescripcion());
        post.setVoteCount(request.getVoteCount());
        post.setSubreddit(subredditRepository.findById(request.getIdSubreddit()).orElseThrow(() -> new
                    ResponseNotFoundException("SUBREDDIT", "ID", request.getIdSubreddit() + "")));


            Post c= postRepository.save(post);
            updateComentarios(request.getId(), request.getComentarios());
            System.out.println("CONSOLAAA:"+post);
            return true;


         }

@Transactional
    public void updateComentarios(Long id, List<CommentRequest> comentarios){
    Optional<Post> optional= postRepository.findById(id);
        if (optional.isPresent()){
            List<Comment> c = optional.get().getComment();
            c.forEach(r->{
                Optional<CommentRequest> exi = comentarios
                        .stream()
                        .filter(com->com.getComentario().contentEquals(r.getComentario()))
                        .findAny();
                if(!exi.isPresent()){
                    commentRepository.delete(r);
                }
            });
            comentarios.forEach(request->{
                String comentario = request.getComentario();
                Optional<Comment> exists =  c.stream()
                        .filter(r->r.getComentario().equalsIgnoreCase(comentario))
                        .findAny();
                if(!exists.isPresent()){
                    Comment save = new Comment();
                    save.setPost(optional.get());
                    save.setComentario(request.getComentario());
                    save.setUserName(request.getUserName());
                    save.setCreatedDate(request.getCreatedDate());
                    save.setUserId(request.getUserId());

                    commentRepository.save(save);
                }
            });

        }else{
            throw new ResponseNotFoundException("POST", "ID:", "" + id+"");
        }


}

    public PostResponse listPost(Long id){
        Optional<Post> optional= postRepository.findById(id);
        if(optional.isPresent()){
            PostResponse response = new PostResponse();
            response.setId(optional.get().getId());
            response.setUserId(optional.get().getUserId());
            response.setPostName(optional.get().getPostName());
            response.setImagen(optional.get().getImagen());
           response.setUserName(optional.get().getUserName());
            response.setUrl(optional.get().getUrl());
            response.setDescripcion(optional.get().getDescripcion());
            response.setVoteCount(optional.get().getVoteCount());
            response.setCreatedDate(optional.get().getCreatedDate());
            response.setIdSubreddit(optional.get().getSubreddit().getId());
            response.setIdimage(optional.get().getImage().getId());
            response.setComentarios(getCommentByPost(optional.get()));
            return response;
        }
        throw new ResponseNotFoundException("Post", "id", id+"");
    }
    private List<CommentRequest> getCommentByPost(Post post) {
        List<Comment> listRe = commentRepository.findAllByPost(post);
        return listRe.stream().map(one -> {
            CommentRequest a = new CommentRequest();
            a.setComentario(one.getComentario());
            a.setId(one.getId());
            a.setIdPost(one.getPost().getId());
            a.setCreatedDate(one.getCreatedDate());
            ///
            a.setUserName(one.getUserName());
            a.setUserId(one.getUserId());
            return a;
        }).collect(Collectors.toList());
    }



    public List<PostResponse> getAll(){
        List<Post> lista= postRepository.findAll();
        return lista.stream()
                .map(this::toPostResponse)
                .collect(Collectors.toList());

    }

    public PostResponse toPostResponse(Post post){
            PostResponse response = new PostResponse();
            response.setId(post.getId());
            response.setUserId(post.getUserId());
            response.setUserName(post.getUserName());
            response.setPostName(post.getPostName());
            response.setUrl(post.getUrl());
            response.setDescripcion(post.getDescripcion());
            response.setVoteCount(post.getVoteCount());
            response.setCreatedDate(post.getCreatedDate());
            response.setIdSubreddit(post.getSubreddit().getId());


          response.setIdimage(post.getImage().getId());
            response.setComentarios(getCommentByPost(post));
            return response;

    }




    public ImagenModelResponse listimgt(Long id){
        Optional<Image> optional= imgRepository.findById(id);
        if(optional.isPresent()){
           ImagenModelResponse response = new ImagenModelResponse();
            response.setId(optional.get().getId());
            response.setName(optional.get().getName());
            response.setType(optional.get().getType());
            response.setImage(optional.get().getImage());
            return response;
        }
        throw new ResponseNotFoundException("Img", "id", id+"");
    }











    @Transactional
    public void deleteById(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        if(!optional.isPresent()) {
            throw new BadRequestException("Post con id: " + id + ", no existe");
        }
       /* List<Long> idsToDelete = new ArrayList<>();

        try{
            idsToDelete.forEach(idComment ->commentRepository.deleteById(idComment));
        }catch (Exception e){
            System.out.println("Error al eliminar comentarios "+e);

        }
        idsToDelete.clear();
        try{
            postRepository.deleteById(optional.get().getId());
        }catch (Exception e) {
            throw new BadRequestException("Error al eliminar el Post");
        }*/

        optional.get().getComment()
                .forEach(d-> deleteComentario(d.getId()));
        postRepository.delete(optional.get());
    }

    public void deleteComentario(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            commentRepository.deleteById(id);
            return;
        }
        throw new BadRequestException("El comentario con el id: " + id + ", no existe");
    }




}

