package com.aluracursos.forohub.domian.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(



        @NotNull
        String email,

        @NotNull
        String contrasenia

) {
}
