package com.aluracursos.forohub.domian.respuesta;

import jakarta.validation.constraints.NotNull;



public record DatosRegistrarRespuesta(

        @NotNull
        Long idTopico,

        @NotNull
        String mensaje,

        @NotNull
        Long idAutor,

        @NotNull
        String    solucion

) {
}
