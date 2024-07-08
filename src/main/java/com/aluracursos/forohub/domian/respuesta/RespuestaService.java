package com.aluracursos.forohub.domian.respuesta;


import com.aluracursos.forohub.domian.curso.CursoRepository;
import com.aluracursos.forohub.domian.topico.TopicoRepository;
import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;








    public ResponseEntity registrarRespuesta(DatosRegistrarRespuesta datos){

        if(!usuarioRepository.findById(datos.idAutor()).isPresent()){
           throw  new ValidacionDeIntegridad("Este usuario no existe");
        }
        if (!topicoRepository.existsById(datos.idTopico())){
             throw new ValidacionDeIntegridad("Este topico no existe");
         }

        var mensaje = datos.mensaje();
        var topico =topicoRepository.findById(datos.idTopico()).get();
        var autor =  usuarioRepository.findById(datos.idAutor()).get();
        var solucion = datos.solucion();

        var respuesta = new Respuesta(mensaje, topico, autor, solucion);

        respuestaRepository.save(respuesta);

        topicoRepository.findByIdTopicoActualizarStatus(datos.idTopico());

        var response = new DatosResponseRespuesta(respuesta);


        return ResponseEntity.ok(response);


    }


    // listar respuestas
    public ResponseEntity<Page<DatosResponseRespuesta>> listarRespuestas(Pageable pageable){
        return ResponseEntity.ok(respuestaRepository
                .findAll(pageable).map(DatosResponseRespuesta::new));

    }


    // obtener respuestas por id
    public ResponseEntity obtenerRespuestaPorId(Long id){

        var respuesta = respuestaRepository.findById(id).get();
        var responseRespuesta = new DatosResponseRespuesta(respuesta);
        return ResponseEntity.ok(responseRespuesta);
    }


    // ELiminar respuesta por id

    public ResponseEntity eleminarRespuesta(Long id){

       respuestaRepository.deleteById(id);

       return ResponseEntity.noContent().build();

    }



}
