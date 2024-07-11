package com.aluracursos.forohub.domian.curso;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Long> {



    Long findIdByNombreCurso(String nombreCurso);


    @Query("SELECT c FROM Curso c  WHERE c.nombreCurso=:nombreCurso AND c.categoria=:categoria")
    Curso findCursoByTituloOMensaje(String nombreCurso, String categoria);


}
