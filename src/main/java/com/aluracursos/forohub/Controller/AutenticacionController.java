package com.aluracursos.forohub.Controller;


import com.aluracursos.forohub.domian.usuario.DatosAutenticacionUsuario;
import com.aluracursos.forohub.domian.usuario.Usuario;
import com.aluracursos.forohub.infra.security.DatosJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    public void  hola(){
        System.out.println("hola mundoooooooooooooo");
    }


/*
    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody  DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.contrasenia());

        System.out.println("authToken:  " +  authToken);
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
*/
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.contrasenia());
        System.out.println("authToken " +  authToken);

        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        System.out.println("usuarioAutenticado " + usuarioAutenticado);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}


/*
luri cuando imprimo por pantalla esto  System.out.println("authToken " +  authToken); me sale esto :
authToken UsernamePasswordAuthenticationToken [Principal=prueba.admin@col.com, Credentials=[PROTECTED], Authenticated=false, Details=null, Granted Authorities=[]]/
 */