package com.aluracursos.forohub.domian.curso;


import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CursoService {


    @Autowired
    private CursoRepository cursoRepository;


    // Registrar Curso
    public ResponseEntity<DatosDetallesCurso> registrarCurso(DatosRegistrarCurso datos) {

        var cursoExiste = cursoRepository.findCursoByTituloOMensaje(datos.nombreCurso(), datos.categoria());
        if (cursoExiste != null) {
            throw new ValidacionDeIntegridad("Este curso ya existe");
        }

        var nombreCurso = datos.nombreCurso();
        var categoria = datos.categoria();
        var curso = new Curso(nombreCurso, categoria);

        cursoRepository.save(curso);
        var response = new DatosDetallesCurso(curso);
        return ResponseEntity.ok(response);
    }


    // Listar cursos

    public ResponseEntity<Page<DatosDetallesCurso>> listarCurso(Pageable pageable) {
        return ResponseEntity.ok(cursoRepository.findAll(pageable).map(DatosDetallesCurso::new));

    }


    //  Obtener curso por id
    public ResponseEntity<DatosDetallesCurso> retornarDatosCurso(Long id) {

        var curso = cursoRepository.findById(id).get();
        var response = new DatosDetallesCurso(curso);
        return ResponseEntity.ok(response);
    }


    //Eliminar curso

    public ResponseEntity eliminarCurso(Long id) {

        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
