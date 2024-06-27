package com.aluracursos.forohub.domian.topico;


import com.aluracursos.forohub.domian.curso.CursoRepository;
import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarTopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public DatosDetallesTopico registrarTopico(DatosRegistrarTopico datos) {

      //datos
        var mensaje = datos.mensaje();
       var titulo = datos.titulo();
       var curso = cursoRepository.findById(datos.idCurso()).get();
       var usuario = usuarioRepository.findById(datos.idUsuario()).get();

      var topico = new Topico(titulo, mensaje, usuario, curso);

      topicoRepository.save(topico);
      return new DatosDetallesTopico(topico);

    }



}
