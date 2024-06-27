package com.aluracursos.forohub.domian.topico;

import jakarta.validation.constraints.NotNull;

public record DatosAtualizarTopico(


        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso,
        String titulo,
        String mensaje

) {


}
