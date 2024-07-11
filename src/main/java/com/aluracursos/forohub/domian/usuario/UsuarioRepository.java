package com.aluracursos.forohub.domian.usuario;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);


    @Modifying
    @Query(value = "UPDATE usuarios SET id_roll_usuario =:perfil  WHERE id =:id", nativeQuery = true)
    public void findByIdUsuarioActualizarRole(Long id, Long perfil);
}
