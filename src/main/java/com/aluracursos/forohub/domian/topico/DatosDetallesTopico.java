package com.aluracursos.forohub.domian.topico;


import java.time.LocalDateTime;

// es el Dto intermediario para devolver la response de los datos de la consulta
// al usuario
public record DatosDetallesTopico(

        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fechaDeCreacion


) {


        public DatosDetallesTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion()
                );
    }


}
