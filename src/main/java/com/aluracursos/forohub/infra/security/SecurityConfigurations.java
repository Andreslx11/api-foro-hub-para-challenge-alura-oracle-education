package com.aluracursos.forohub.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfigurations {


    @Autowired
    private SecurityFilter securityFilter;



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/topicos/registrar").permitAll()// Crear tópicos
                        .requestMatchers(HttpMethod.GET, "/topicos/listar").permitAll() // Consultar tópicos
                        .requestMatchers(HttpMethod.GET, "/topico/listar/{id}").permitAll() // Consultar tópico
                        .requestMatchers(HttpMethod.PUT, "/topicos/atualizar/{id}").permitAll() // Actualizar tópicos
                        .requestMatchers(HttpMethod.DELETE, "/topicos/eliminar/{id}").permitAll() // Eliminar tópicos

                        .requestMatchers(HttpMethod.POST, "/respuesta/registrar").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/respuesta/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/respuesta/listar/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/respuesta/listar/{id}").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/respuesta/eliminar/{id}").hasAnyRole("INSTRUCTOR", "ADMIN")


                        .requestMatchers(HttpMethod.POST, "/usuario/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/listar/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/usuario/atualizar/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/usuario/atualizar-role/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Usuario/eliminar/{id}").permitAll() // Este en la clase UsuarioService
                        // tiene una logica para que solo el usuario propio, admin o instructor puedan eliminar


                        .requestMatchers(HttpMethod.POST, "/curso/registrar").hasAnyRole("ADMIN","INSTRUCTOR" )
                        .requestMatchers(HttpMethod.GET, "/curso/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/curso/listar/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/curso/atualizar/{id}").hasAnyRole("ADMIN","INSTRUCTOR")
                        .requestMatchers(HttpMethod.DELETE, "/curso/eliminar/{id}").hasAnyRole("ADMIN","INSTRUCTOR")

                        .anyRequest()
                        .authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
