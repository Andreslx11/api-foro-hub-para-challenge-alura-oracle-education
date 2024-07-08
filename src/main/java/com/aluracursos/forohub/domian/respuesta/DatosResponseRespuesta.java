package com.aluracursos.forohub.domian.respuesta;

public record DatosResponseRespuesta(

        Long id,

        Long idTopico,

        String mensaje,

        Long idAutor,

        String    solucion


) {

    public DatosResponseRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTopico().getId(), respuesta.getMensaje(),
                respuesta.getAutor().getId(), respuesta.getSolucion()
        );
    }
}
