package com.aluracursos.forohub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{

    public ValidacionDeIntegridad(String message) {
        super(message);
    }
}
