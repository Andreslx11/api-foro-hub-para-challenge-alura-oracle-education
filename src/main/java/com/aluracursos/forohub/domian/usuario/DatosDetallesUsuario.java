package com.aluracursos.forohub.domian.usuario;



public record DatosDetallesUsuario(

        Long id,
        String  nombre,
        String  email,
        String role
) {

    public DatosDetallesUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                usuario.getPerfil().getRollUsuario());

    }
}
