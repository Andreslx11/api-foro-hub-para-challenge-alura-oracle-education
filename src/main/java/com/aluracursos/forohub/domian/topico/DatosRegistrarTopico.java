package com.aluracursos.forohub.domian.topico;

import jakarta.validation.constraints.NotNull;



// es el dto irtermediario desde cliente al backend para registrar un topico
public record DatosRegistrarTopico(


           @NotNull
           Long idUsuario,

           @NotNull
           Long idCurso,

           @NotNull
           String titulo,

           @NotNull
           String mensaje


) {
}
