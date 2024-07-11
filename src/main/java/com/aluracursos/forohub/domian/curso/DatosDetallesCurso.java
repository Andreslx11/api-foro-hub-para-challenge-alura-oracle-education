package com.aluracursos.forohub.domian.curso;



public record DatosDetallesCurso(

        Long Id,

        String nombreCurso,

        String categoria

) {

     public DatosDetallesCurso(Curso curso){
         this(curso.getId(), curso.getNombreCurso(), curso.getCategoria());
     }
}
