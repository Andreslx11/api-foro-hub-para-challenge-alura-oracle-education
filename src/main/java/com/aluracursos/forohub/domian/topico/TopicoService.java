package com.aluracursos.forohub.domian.topico;

import com.aluracursos.forohub.domian.curso.CursoRepository;
import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TopicoService {



    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    // Registrar topico
    public ResponseEntity registrarTopico(DatosRegistrarTopico datos) {
        //datos
        var mensaje = datos.mensaje();
        var titulo = datos.titulo();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();

        var topico = new Topico(titulo, mensaje, usuario, curso);

        topicoRepository.save(topico);
         var response = new DatosDetallesTopico(topico);
        return ResponseEntity.ok(response);
    }


    // listar topicos

    public ResponseEntity<Page<DatosDetallesTopico>> listarTopicos( Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosDetallesTopico::new));

    }



    public  ResponseEntity<DatosDetallesTopico> retornarDatosTopico( Long id){

        var topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosDetallesTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }


    public ResponseEntity eliminarTopico( Long id ){

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }



}
