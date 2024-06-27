package com.aluracursos.forohub.domian.usuario;

import com.aluracursos.forohub.domian.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table( name = "usuarios")
@Entity(name = "Usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of=  "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String contrasenia;


    @OneToOne
    @JoinColumn(name= "id_roll_usuario", referencedColumnName = "id")
    private Perfil perfil;





}
