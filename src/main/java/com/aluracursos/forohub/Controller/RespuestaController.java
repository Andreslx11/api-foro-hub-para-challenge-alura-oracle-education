package com.aluracursos.forohub.Controller;


import com.aluracursos.forohub.domian.respuesta.*;
import com.aluracursos.forohub.domian.topico.DatosDetallesTopico;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Respuesta", description = "Endpoint relacionados con respuesta")
@SecurityRequirement(name = "bearer-key")
@RestController
@ResponseBody
@RequestMapping
public class RespuestaController {

     @Autowired
     private RespuestaService respuestaService;

     @Autowired
     private RespuestaRepository respuestaRepository;

     @PostMapping("/respuesta/registrar")
     @Transactional
     @Operation(summary = "Respuesta", description = "Proporciona el endpoint para" +
             "registrar una respuesta de un topico")
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistrarRespuesta datos){
          return    respuestaService.registrarRespuesta(datos);
    }


    // Listar repuestas

     @GetMapping("/respuesta/listar")
     @Operation(summary = "Listar respuestas", description = "Endpoint de respuestas")
    public ResponseEntity<Page<DatosResponseRespuesta>> listarRespuestas(@PageableDefault(size = 10) Pageable pageable){
         return respuestaService.listarRespuestas(pageable);
    }


    // listar respuesta por id
    @GetMapping("/respuesta/listar/{id}")
    @Operation(summary = "Obtener respuesta por Id", description =" Proporciona el endpoint" +
            " para obtener un respuesta por su id")
    public  ResponseEntity<DatosDetallesTopico> retornarDatosTopico(@PathVariable Long id){
        return respuestaService.obtenerRespuestaPorId(id);
    }


    // Actualizar respuesta
    @PutMapping("/respuesta/actualizar/{id}")
    @Transactional
    @Operation(summary = "Actualizar respuesta por Id", description =" Proporciona el endpoint" +
            " para actualizar un respuesta por su id")
    public ResponseEntity actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid
                                           DatosActualizarRespuesta datos){
         var respuesta = respuestaRepository.getReferenceById(id);
         respuesta.actualizarRespuesta(datos);
         return ResponseEntity.ok(new DatosResponseRespuesta(respuesta));

    }


    // eliminar respuesta por id
    @DeleteMapping("/respuesta/eliminar/{id}")
    @Transactional
    @Operation( summary = "Eliminar respuesta por id" , description =" Proporciona el endpoint\" +\n" +
            "            \" para actualizar un respuesta por su id")
    public ResponseEntity eliminarRespuesta( @PathVariable Long id){

         return respuestaService.eleminarRespuesta(id);
    }


}
