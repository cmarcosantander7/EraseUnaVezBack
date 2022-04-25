package com.proyectointegrador.proyecto.Service;

import com.proyectointegrador.proyecto.DTO.SubredditRequest;
import com.proyectointegrador.proyecto.DTO.SubredditResponse;
import com.proyectointegrador.proyecto.Models.Subreddit;
import com.proyectointegrador.proyecto.Repositories.SubredditRepository;
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
public class SubredditService {
    @Autowired
    private SubredditRepository subredditRepository;

    public void save (SubredditRequest request){
        Subreddit sub =new Subreddit();
        sub.setUserId(request.getUserId());
        sub.setNombre(request.getNombre());
        sub.setDescripcion(request.getDescripcion());
        sub.setCreatedDate(request.getCreatedDate());
        try{
            subredditRepository.save(sub);
        }catch(Exception e){
            throw new BadRequestException("No se guardó subreddit " + e);
        }
    }

    public Boolean update (SubredditRequest request){
        Optional<Subreddit> optional=subredditRepository.findById(request.getId());
        if(optional.isPresent()){
            optional.get().setNombre(request.getNombre());
            optional.get().setDescripcion(request.getDescripcion());
            optional.get().setCreatedDate(request.getCreatedDate());
            try{
                Subreddit c= subredditRepository.save(optional.get());
                return true;

            }catch(Exception ex){
                throw new BadRequestException("No se actualizó el subreddit" + ex);
            }
        }
        throw new ResponseNotFoundException("SUBREDDIT", "ID:", "" + request.getId());
    }

    public SubredditResponse listSubreddit (Long id){
        Optional<Subreddit> optional=subredditRepository.findById(id);
        if(optional.isPresent()){
            SubredditResponse response = new SubredditResponse();
            response.setId(optional.get().getId());
            response.setUserId(optional.get().getUserId());
            response.setNombre(optional.get().getNombre());
            response.setDescripcion(optional.get().getDescripcion());
            response.setCreatedDate(optional.get().getCreatedDate());
            return response;
        }
        throw new ResponseNotFoundException("SUBREDDIT", "ID:", "" + id);
    }


    public List<SubredditResponse> getAll(){
        List<Subreddit> lista= subredditRepository.findAll();
        return lista.stream().map(optional->{
            SubredditResponse response = new SubredditResponse();
            response.setId(optional.getId());
            response.setUserId(optional.getUserId());
            response.setNombre(optional.getNombre());
            response.setDescripcion(optional.getDescripcion());
            response.setCreatedDate(optional.getCreatedDate());
            return response;
        }).collect(Collectors.toList());
    }
}
