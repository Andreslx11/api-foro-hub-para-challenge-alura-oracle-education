package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;


    // esta clase se inyectar en SecurityConfigurations

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //obtener token del encanbezado de la peticion del cliente
        String authHeader = request.getHeader("Authorization");

        System.out.println("segundo filtro:  " +  authHeader );
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");

            var nombreEmail= tokenService.getSubject(token);


            if(nombreEmail != null ){
              var usuario = usuarioRepository.findByEmail(nombreEmail);
              var authentication = new UsernamePasswordAuthenticationToken(usuario,
                      null, usuario.getAuthorities()); // recordar, para forzar el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // para permitir que la solicitud siga el flujo por los filtros
      filterChain.doFilter(request, response);
    }
}
