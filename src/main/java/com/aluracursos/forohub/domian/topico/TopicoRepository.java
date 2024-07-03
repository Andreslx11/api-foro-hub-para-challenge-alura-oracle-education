package com.aluracursos.forohub.domian.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {





    @Query("SELECT t FROM Topico t  WHERE t.titulo=:titulo OR t.mensaje=:mensaje")
   public   Topico findTopicoByTituloOMensaje(String titulo, String mensaje);
}

