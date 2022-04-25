package com.proyectointegrador.proyecto.security;

import com.proyectointegrador.proyecto.Service.AuthService;
import com.proyectointegrador.proyecto.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/*","/csrf","/",

                       
                        "/check/upload",
                        "/upload/image/**",
                        "/uploadFile/**",
                        "/upload",
                        "/getFiles",
                        "/downloadFile/**",
                        "/files/**",
                        "/getFiles/**",
                        "/files",
                        "/upload/image",
                        "/get/image/info/**",
                        "/get/image/**",
                        "/check/get/**",
                        "/fi/**",
                        "/get/imagessss/**",
                        "/*")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ;
    }

}