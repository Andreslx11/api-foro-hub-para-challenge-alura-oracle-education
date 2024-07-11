package com.aluracursos.forohub.domian.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;




public interface TopicoRepository extends JpaRepository<Topico, Long> {


    @Query("SELECT t FROM Topico t  WHERE t.titulo=:titulo OR t.mensaje=:mensaje")
    public Topico findTopicoByTituloOMensaje(String titulo, String mensaje);



    @Modifying
    @Query(value = "UPDATE topicos SET status = 1 WHERE id = :id", nativeQuery = true)
    public void findByIdTopicoActualizarStatus(Long id);



    @Modifying
    @Query(value="DELETE FROM topicos t WHERE t.id_autor_topico =:idUsuario", nativeQuery = true)
    public void eliminarTopicosPorUsuario( Long idUsuario);
}


