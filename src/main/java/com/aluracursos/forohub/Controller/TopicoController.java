package com.aluracursos.forohub.Controller;


import com.aluracursos.forohub.domian.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping
public class TopicoController {


    @Autowired
    private  TopicoService service;
    @Autowired
    private TopicoRepository topicoRepository;

    // registrar topico
    @PostMapping("/topicos/registrar")
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistrarTopico datosTopico){
        return service.registrarTopico(datosTopico);
    }


    // listar topicos
    @GetMapping("/topicos/listar")
    public ResponseEntity<Page<DatosDetallesTopico>> listarMedico(@PageableDefault(size= 10) Pageable pageable){
        return service.listarTopicos(pageable);

    }

    // listar topico por id
    @GetMapping("/topicos/listar/{id}")
    public  ResponseEntity<DatosDetallesTopico> retornarDatosTopico(@PathVariable Long id){
        return service.retornarDatosTopico(id);
    }


    // atualizar topico
    @PutMapping("/topicos/atualizar/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosAtualizarTopico datos){

        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datos);
        return ResponseEntity.ok(new DatosDetallesTopico(topico));

    }


    // Elimanar topico de la base de datos
    @DeleteMapping("/topicos/eliminar/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id ){
        return service.eliminarTopico(id);

    }






// para probar la comunicacion
/*
    @PostMapping
    public void   registrarTopico(){

        System.out.println("hola mundoooooooooooooo");

    }
*/



}
