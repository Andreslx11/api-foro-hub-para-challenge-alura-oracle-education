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
    private RegistrarTopicoService service;
    @Autowired
    private TopicoRepository topicoRepository;

    // registrar topico
    @PostMapping("/topicos/registrar")
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistrarTopico datosTopico){

        System.out.println("hola mundoooooooooooooo");


      var response = service.registrarTopico(datosTopico);

      return ResponseEntity.ok(response);
    }


    // listar topicos

    @GetMapping("/topicos/listar")
    public ResponseEntity<Page<DatosDetallesTopico>> listarMedico(@PageableDefault(size= 10)Pageable pageable){

        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosDetallesTopico::new));

    }

    // listar topico por id
    @GetMapping("/topicos/listar/{id}")
    public  ResponseEntity<DatosDetallesTopico> retornarDatosTopico(@PathVariable Long id){

     var topico = topicoRepository.getReferenceById(id);
     var datosTopico = new DatosDetallesTopico(topico);
     return ResponseEntity.ok(datosTopico);
    }


    // atualizar topico

//    @PutMapping("/topicos/atualizar/{id}")
    @PutMapping("/topicos")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosAtualizarTopico datos){

        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datos);

        return ResponseEntity.ok(new DatosDetallesTopico(topico));

    }


    // Elimanar topico de la base de datos

//    @DeleteMapping("/topicos/eliminar/{id}")
    @DeleteMapping("/topicos")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id ){

         topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }






// para probar la comunicacion
/*
    @PostMapping
    public void   registrarTopico(){

        System.out.println("hola mundoooooooooooooo");



    }
*/
}
