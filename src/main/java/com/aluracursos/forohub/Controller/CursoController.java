package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domian.curso.*;
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


@Tag(name = "Curso", description = "Endpoints relacionados con cursos")
@SecurityRequirement(name = "bearer-key")
@RestController
@ResponseBody
@RequestMapping
public class CursoController {


    @Autowired
    private CursoService service;

    @Autowired
    private CursoRepository cursoRepository;


    // registrar curso
    @PostMapping("/curso/registrar")
    @Transactional
    @Operation(summary = "Registrar", description = "Proporciona el endpoint para registrar un nuevo curso")
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistrarCurso datosCurso) {
        return service.registrarCurso(datosCurso);
    }


    // listar curso
    @GetMapping("/curso/listar")
    @Operation(summary = "Listar Cursos ", description = "Proporciona el endpoint para listar los cursos")
    public ResponseEntity<Page<DatosDetallesCurso>> listarCurso(@PageableDefault(size = 10) Pageable pageable) {
        return service.listarCurso(pageable);

    }

    // listar curso por id
    @GetMapping("/curso/listar/{id}")
    @Operation(summary = "Obtener Curso por Id", description = " Proporciona el endpoint para obtener un curso por su id")
    public ResponseEntity<DatosDetallesCurso> retornarDatosCurso(@PathVariable Long id) {
        return service.retornarDatosCurso(id);
    }


    // atualizar curso
    @PutMapping("/curso/atualizar/{id}")
    @Transactional
    @Operation(summary = "Actualizar Curso por Id", description = " Proporciona el endpoint para Actualizar un curso por su id")
    public ResponseEntity actualizarCurso(@PathVariable Long id, @RequestBody @Valid DatosActualizarCurso datos) {

        var curso = cursoRepository.getReferenceById(id);
        curso.actualizarCurso(datos);
        return ResponseEntity.ok(new DatosDetallesCurso(curso));

    }


    // Elimanar topico de la base de datos
    @DeleteMapping("/curso/eliminar/{id}")
    @Transactional
    @Operation(summary = "Eliminar curso por Id", description = " Proporciona el endpoint para eliminar un curso por su id")
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        return service.eliminarCurso(id);

    }

}
