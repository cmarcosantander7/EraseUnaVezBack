package com.proyectointegrador.proyecto.Service;

import com.proyectointegrador.proyecto.DTO.RegisterRequest;
import com.proyectointegrador.proyecto.DTO.UserRequest;
import com.proyectointegrador.proyecto.DTO.UserResponse;
import com.proyectointegrador.proyecto.Models.Usuario;
import com.proyectointegrador.proyecto.Repositories.UsuarioRepository;
import com.proyectointegrador.proyecto.exception.ResponseNotFoundException;
import com.proyectointegrador.proyecto.security.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    //REGISTRO

    @Transactional
    public UserResponse signup (RegisterRequest registerRequest) throws Exception {
        Usuario newUser = new Usuario();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setClave(registerRequest.getClave());
        newUser.setUsername(registerRequest.getUsername());
        if(!getUsuario(registerRequest.getEmail())){
            Usuario usuario=usuarioRepository.save(newUser);

            if(usuario!=null){
                return  new UserResponse(usuario.getId(), usuario.getEmail(),usuario.getClave(),generateTokenSignUp(registerRequest),usuario.getUsername());
            }else{
                log.error("No se puedo guardar el usuario con email: {}", registerRequest.getEmail());
                throw new Exception("No se pudo guardar el usuario");
            }
        }else {
            log.error("El email ya está registrado: {}", registerRequest.getEmail());
            throw new Exception("El email ingresado, ya esta registrado, si el correo le pertenece contactenos a: 0995521556");
        }

    }

    private boolean getUsuario(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public String generateTokenSignUp(RegisterRequest registerRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getEmail())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en signup de usuario con email: {}", registerRequest.getEmail());
            throw new Exception("INAVALID");
        }
        return jwtUtil.generateToken(registerRequest.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(usuario.get().getEmail(), usuario.get().getEmail(), new ArrayList<>());
    }

    //INICIAR SESIÓN
    @Transactional
    public UserResponse login(UserRequest userRequest) throws Exception {
        Optional<Usuario> optional = usuarioRepository.findByEmail(userRequest.getEmail());

        if (optional.isPresent() ) {
            Usuario usuario = optional.get();
            if(usuario!=null){
                if(userRequest.getClave().equals(usuario.getClave())){
                    return  new UserResponse(usuario.getId(), usuario.getEmail(),usuario.getClave(),generateTokenLogin(userRequest),usuario.getUsername());
                }else{
                    throw new Exception("La contraseña es incorrecta");
                }


            }else{
                throw new Exception("Usuario null login");
            }
        }else{
            log.info("EMAIL NO EXISTE");
        }
        log.info("AFUERA LOGIN");
        return null;
    }

    public String generateTokenLogin(UserRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getEmail())
            );
        } catch (Exception ex) {
            log.error("INVALID: error al generar token en login de usuario con email: {}", userRequest.getEmail());
            throw new Exception("INAVALID");
        }
        return jwtUtil.generateToken(userRequest.getEmail());
    }

    public UserResponse getUser(String email) {
        Optional<Usuario> optional = usuarioRepository.findByEmail(email);
        if (optional.isPresent() ) {
            Usuario usuario = optional.get();
            return new UserResponse(usuario.getId(), usuario.getEmail(), usuario.getClave(), usuario.getUsername());
        }
        throw new ResponseNotFoundException("Usuario", "email", email);
    }


}
