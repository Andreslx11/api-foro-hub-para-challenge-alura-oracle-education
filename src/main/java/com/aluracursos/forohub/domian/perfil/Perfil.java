package com.aluracursos.forohub.domian.perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="roll_usuario")
    private String rollUsuario;
}
