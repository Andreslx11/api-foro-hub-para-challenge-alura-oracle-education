package com.aluracursos.forohub.domian.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosUsuario(

        @NotNull
        String email,

        @NotNull
        String contrasenia

) {
}
