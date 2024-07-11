package com.aluracursos.forohub.domian.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarUsuario(


              @NotNull
              String  nombre,

              @NotNull
              @Email
              String  email,

              @NotNull
              String  contrasenia

) {
}
