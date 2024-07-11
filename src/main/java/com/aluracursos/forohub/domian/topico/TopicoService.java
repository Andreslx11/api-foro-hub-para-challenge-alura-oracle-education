package com.aluracursos.forohub.domian.topico;

import com.aluracursos.forohub.domian.curso.CursoRepository;
import com.aluracursos.forohub.domian.respuesta.DatosRegistrarRespuesta;
import com.aluracursos.forohub.domian.topico.validaciones.IValidadorDeTopicos;
import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TopicoService {


    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    List<IValidadorDeTopicos> validadores;

    // Registrar topico
    public ResponseEntity<DatosDetallesTopico> registrarTopico(DatosRegistrarTopico datos) {

        if (!cursoRepository.findById(datos.idCurso()).isPresent()) {
            throw new ValidacionDeIntegridad("Este curso no fue encontrado");
        }

        if (datos.idUsuario() != null && !usuarioRepository.existsById(datos.idUsuario())) {
            throw new ValidacionDeIntegridad("El id para usuario no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datos));

        //Datos
        var mensaje = datos.mensaje();
        var titulo = datos.titulo();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();

        var topico = new Topico(titulo, mensaje, usuario, curso);

        topicoRepository.save(topico);
        var response = new DatosDetallesTopico(topico);
        return ResponseEntity.ok(response);
    }


    // Listar topicos

    public ResponseEntity<Page<DatosDetallesTopico>> listarTopicos(Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosDetallesTopico::new));

    }


    //  Obtener topico por id
    public ResponseEntity<DatosDetallesTopico> retornarDatosTopico(Long id) {

        var topico = topicoRepository.findById(id).get();
        var datosTopico = new DatosDetallesTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }



    //Eliminar topico

    public ResponseEntity eliminarTopico(Long id) {

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }


    // Actualizar topico
    private void actualizarStatusTopico(DatosRegistrarRespuesta datos) {
        topicoRepository.findByIdTopicoActualizarStatus(datos.idTopico());
    }


}
