package com.aluracursos.forohub.domian.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {



//    @Query("""
//    Select c    From Curso c
//    where c.nombreCurso=:nombreCurso
//
//           """)
//    Long findNameColumn(String nombreCurso);


    Long findIdByNombreCurso(String nombreCurso);




//    @Query("""
//            select m from Medico m
//            where m.activo= 1
//            and
//            m.especialidad=:especialidad
//            and
//            m.id not in(
//                select c.medico.id from Consulta c
//                where
//                c.fecha=:fecha
//            )
//            order by rand()
//            limit 1
//            """)
//    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

}
