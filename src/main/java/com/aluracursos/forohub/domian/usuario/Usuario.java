package com.aluracursos.forohub.domian.usuario;

import com.aluracursos.forohub.domian.perfil.Perfil;
import com.aluracursos.forohub.domian.perfil.PerfilRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table( name = "usuarios")
@Entity(name = "Usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of=  "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String contrasenia;


    @OneToOne
    @JoinColumn(name= "id_roll_usuario", referencedColumnName = "id")
    private Perfil perfil;




    public Usuario( String nombre, String email, String contrasenia, Perfil perfil) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
        this.perfil = perfil;
    }


    public void actualizarUsuario(DatosActualizarUsuario datos){

        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.email() != null){
            this.email = datos.email();
        }
        if(datos.contrasenia() != null){
            this.contrasenia = datos.contrasenia();
        }

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(perfil.getRollUsuario()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
