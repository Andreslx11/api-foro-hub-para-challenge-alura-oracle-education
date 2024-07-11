package com.aluracursos.forohub.domian.curso;

import jakarta.validation.constraints.NotNull;

public record DatosRegistrarCurso(

        @NotNull
        String nombreCurso,
        @NotNull
        String categoria
) {
}
