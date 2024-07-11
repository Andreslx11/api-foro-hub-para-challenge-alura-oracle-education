package com.aluracursos.forohub.Controller;



import com.aluracursos.forohub.domian.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Usuario", description = "Endpoints relacionados con Usuario")
@SecurityRequirement(name = "bearer-key")
@RestController
@ResponseBody
@RequestMapping
public class UsuarioController {



    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registrar Usuario
    @PostMapping("/usuario/registrar")
    @Transactional
    @Operation(summary = "Registrar", description = "Proporciona el endpoint para registrar un nuevo usuario")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datosUsuario){
        return service.registrarUsuario(datosUsuario);
    }


    // Listar Usario
    @GetMapping("/usuario/listar")
    @Operation(summary = "Listar Topicos ", description =  "Proporciona el endpoint para listar los usuario")
    public ResponseEntity<Page<DatosDetallesUsuario>> listarUsuarios(@PageableDefault(size= 10) Pageable pageable){
        return service.listarUsuario(pageable);

    }

    // Listar usuario por id
    @GetMapping("/usuario/listar/{id}")
    @Operation(summary = "Obtener Usuario por Id", description =" Proporciona el endpoint para obtener un usuario por su id")
    public  ResponseEntity<DatosDetallesUsuario> retornarDatosUsuario(@PathVariable Long id){
        return service.retornarDatosUsuario(id);
    }


    // Actualizar usuario
    @PutMapping("/usuario/atualizar/{id}")
    @Transactional
    @Operation(summary = "Actualizar Usuario por Id", description =" Proporciona el endpoint para Actualizar un usuario por su id")
    public ResponseEntity actualizarUsuario(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuario datos){

        var usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarUsuario(datos);
        return ResponseEntity.ok(new DatosDetallesUsuario(usuario));

    }


    // Elimanar usuario de la base de datos
    @DeleteMapping("/usuario/eliminar/{id}")
    @Transactional
    @Operation(summary = "Eliminar Usuario por Id", description =" Proporciona el endpoint para eliminar un usuario por su id")
    public ResponseEntity eliminarUsuario(@PathVariable Long id, Authentication authentication ){
        return service.eliminarUsuario(id, authentication );

    }


    // Actualizar Role
    @PutMapping("/usuario/atualizar-role/{id}")
    @Transactional
    @Operation(summary = "Actualizar role usuario por Id", description =" Proporciona el endpoint para Actualizar role usuario por su id")
    public ResponseEntity actualizarUsuarioRole(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuarioRole datos){

        usuarioRepository.findByIdUsuarioActualizarRole(id, datos.perfil());
        var usuario = usuarioRepository.findById(id).get();
        return ResponseEntity.ok(new DatosDetallesUsuario(usuario));

    }


}
