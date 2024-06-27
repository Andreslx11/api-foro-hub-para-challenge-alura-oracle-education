package com.aluracursos.forohub.domian.curso;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre_curso")
    private String nombreCurso;

    private String categoria;



}



