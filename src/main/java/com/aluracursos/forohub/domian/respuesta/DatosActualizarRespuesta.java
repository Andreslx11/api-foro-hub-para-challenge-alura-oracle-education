package com.aluracursos.forohub.domian.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(



        @NotNull
        Long idTopico,

        String mensaje,

        @NotNull
        Long idAutor,

        String    solucion
) {
}
