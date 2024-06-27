package com.aluracursos.forohub.domian.topico;

import com.aluracursos.forohub.domian.curso.Curso;
import com.aluracursos.forohub.domian.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    @Column(name = "fecha_de_creacion")
    private LocalDateTime fechaDeCreacion;

    private Boolean status;

    @OneToOne
    @JoinColumn(name = "id_autor_topico", referencedColumnName = "id")
    private Usuario autor;

    @OneToOne
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    private Curso curso;


    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {

        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = false;
        this.autor = autor;
        this.curso = curso;
    }

    // atualizar topico

    public void actualizarTopico(DatosAtualizarTopico datos) {

        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }

    }
}