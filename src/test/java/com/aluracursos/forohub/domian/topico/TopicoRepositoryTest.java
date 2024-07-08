package com.aluracursos.forohub.domian.topico;

import com.aluracursos.forohub.domian.curso.Curso;
import com.aluracursos.forohub.domian.curso.CursoRepository;
import com.aluracursos.forohub.domian.usuario.Usuario;
import com.aluracursos.forohub.domian.usuario.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    @DisplayName("Debe retornar null  cuando se realice la consulta en la base datos")
    void findTopicoByTituloOMensajeEscenario1() {

        // given
        var titulo = "titulo prueba escenario 1";
        var mensaje = "mensaje prueba escenario 1";
        var titulo2 = "titulo prueba escenario 1 null";
        var mensaje2 = "mensaje prueba escenario 1 null";
        var usuario = usuarioRepository.findById(1L).get();
        var curso = cursoRepository.findById(1L).get();


        // When
        var topico = registrarTopico(titulo, mensaje, usuario, curso);
        var topicoRegistrado = topicoRepository.findTopicoByTituloOMensaje(titulo2, mensaje2);

        assertThat(topicoRegistrado).isNull();

    }


    @Test
    @DisplayName("Debe retornar un topico  cuando se realice la consulta en la base datos")
    void findTopicoByTituloOMensajeEscenario2() {

        // given
        var titulo = "titulo prueba escenario 2";
        var mensaje = "mensaje prueba escenario 2";
        var usuario = usuarioRepository.findById(1L).get();
        var curso = cursoRepository.findById(1L).get();


        // When
        var topico = registrarTopico(titulo, mensaje, usuario, curso);
        var topicoRegistrado = topicoRepository.findTopicoByTituloOMensaje(titulo, mensaje);

        assertThat(topicoRegistrado).isEqualTo(topico);

    }

    private Topico registrarTopico(String titulo, String mensaje, Usuario usuario, Curso curso) {

        return testEntityManager.persist(new Topico(titulo, mensaje, usuario, curso));

    }


}