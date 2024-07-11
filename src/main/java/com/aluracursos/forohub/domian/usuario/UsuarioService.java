package com.aluracursos.forohub.domian.usuario;

import com.aluracursos.forohub.domian.perfil.PerfilRepository;
import com.aluracursos.forohub.domian.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private TopicoRepository topicoRepository;





    // Registrar Usuario
    public ResponseEntity<DatosDetallesUsuario> registrarUsuario(DatosRegistrarUsuario datos) {
       BCryptPasswordEncoder  encriptacion =new BCryptPasswordEncoder();

        var nombre = datos.nombre();
        var email = datos.email();

        var contrasenia = encriptacion.encode(datos.contrasenia());
        var perfil = perfilRepository.findById(3L).get();

        var usuario = new Usuario(nombre, email, contrasenia, perfil);
        usuarioRepository.save(usuario);
        var response = new DatosDetallesUsuario(usuario);
        return ResponseEntity.ok(response);
    }


    // Listar usuario

    public ResponseEntity<Page<DatosDetallesUsuario>> listarUsuario(Pageable pageable) {
        return ResponseEntity.ok(usuarioRepository.findAll(pageable).map(DatosDetallesUsuario::new));

    }


    //  Obtener usuario por id
    public ResponseEntity<DatosDetallesUsuario> retornarDatosUsuario(Long id) {

        var usuario = usuarioRepository.findById(id).get();
        var response = new DatosDetallesUsuario(usuario);
        return ResponseEntity.ok(response);
    }


   // Eliminar usuario
    public ResponseEntity eliminarUsuario(Long id, Authentication authentication) {

        //usuario actual
        Usuario usuarioActual = (Usuario) authentication.getPrincipal();

        if(usuarioActual.getPerfil().equals("ADMIN") || usuarioActual.getPerfil().equals("INSTRUCTOR")){
            topicoRepository.eliminarTopicosPorUsuario(id);
            usuarioRepository.deleteById(id);
        }
        else if(usuarioActual.getId().equals(id)){
            topicoRepository.eliminarTopicosPorUsuario(id);
            usuarioRepository.deleteById(id);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para eliminar este usario");
        }

        return ResponseEntity.noContent().build();

    }


    public void actualizarUsuarioRole(Long id ,DatosActualizarUsuarioRole datos){

       usuarioRepository.findByIdUsuarioActualizarRole(id, datos.perfil());

    }

}
