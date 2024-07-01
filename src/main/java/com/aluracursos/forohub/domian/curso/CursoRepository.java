package com.aluracursos.forohub.domian.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {



    Long findIdByNombreCurso(String nombreCurso);


}
