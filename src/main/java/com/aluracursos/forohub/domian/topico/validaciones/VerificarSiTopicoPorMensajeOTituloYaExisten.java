package com.aluracursos.forohub.domian.topico.validaciones;


import com.aluracursos.forohub.domian.topico.DatosRegistrarTopico;
import com.aluracursos.forohub.domian.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificarSiTopicoPorMensajeOTituloYaExisten implements IValidadorDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;


    @Override
    public void validar(DatosRegistrarTopico datos) {

            var topico = topicoRepository.findTopicoByTituloOMensaje(datos.titulo(), datos.mensaje());

            if(topico != null){
                throw new ValidationException("El topico ya existe en la base datos con id: " + topico.getId());
            }

    }


}


