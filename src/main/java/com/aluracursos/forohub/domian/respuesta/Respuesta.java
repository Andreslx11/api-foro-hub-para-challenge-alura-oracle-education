package com.aluracursos.forohub.domian.respuesta;


import com.aluracursos.forohub.domian.topico.Topico;
import com.aluracursos.forohub.domian.topico.TopicoRepository;
import com.aluracursos.forohub.domian.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Table(name ="respuestas")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= "id")
public class Respuesta {



      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String mensaje ;

      @OneToOne
      @JoinColumn(name ="id_topico", referencedColumnName = "id")
      private Topico topico;

      @Column(name= "fecha_de_creacion")
      private LocalDateTime fechaDeCreacion;

      @OneToOne
      @JoinColumn(name = "id_autor_respuesta", referencedColumnName = "id")
      private Usuario autor;

      private String    solucion;




      public Respuesta(String mensaje, Topico topico, Usuario autor, String solucion) {
            this.mensaje = mensaje;
            this.topico = topico;
            this.fechaDeCreacion = LocalDateTime.now();
            this.autor = autor;
            this.solucion = solucion;
      }


      // Actualizar respuesta

      public void actualizarRespuesta(DatosActualizarRespuesta datos){

            if(datos.mensaje() != null){
                  this.mensaje = datos.mensaje();
            }

            if(datos.solucion() != null){
                  this.solucion = datos.solucion();
            }
      }


}
