package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domian.topico.DatosDetallesTopico;
import com.aluracursos.forohub.domian.topico.DatosRegistrarTopico;
import com.aluracursos.forohub.domian.topico.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {


    // Es como un "simulador" de peticiones HTTP en Spring Boot.
    @Autowired
    private MockMvc mockMvc;


    // Va tomar el objeto tipo Objet y lo va convertir en un json, serialización
    @Autowired
    private JacksonTester<DatosRegistrarTopico> registrarTopicoJacksonTester;


    // Aqui va hacer  lo contrario va tomar el json y lo va converti en Objet, deserilización
    @Autowired
    private JacksonTester<DatosDetallesTopico> datosDetallesTopicoJacksonTester;

    //@MockBean en Spring Boot sirve para crear una versión simulada (mock) de una clase o interfaz.
    @MockBean
    private TopicoService topicoService;

    @Autowired
    private TopicoController topicoController;


    @Test
    @DisplayName("Debe retornar estado http 400 bad request cuando cunado lo datos ingresados" + "sean invalidos")
    @WithMockUser
        // para simular un usuario autentico
    void registrarTopicoEscenario1() throws Exception {
        // Given - When
        var response = mockMvc.perform(post("/topicos/registrar")).andReturn().getResponse();

        // Then
        // Verificar que el estado de esta requision sea del estado 400, BAD_REQUEST es igual al estado 400
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName("Debe retornar estado http 200 cuando cunado lo datos ingresados" + "son validos")
    @WithMockUser
        // Para simular un usuario autentico
    void registrarTopicoEscenario2() throws Exception {
        // Given

        var fecha = LocalDateTime.now();
        var titulo = "Error de hola mundo";
        var mensaje = "El error se presento por ende ando ";
        var datos = new DatosDetallesTopico(null, titulo, mensaje, fecha);

        // When

        when(topicoService.registrarTopico(any())).thenReturn(ResponseEntity.ok(datos)); // El error era que en mi
        // clase topicoService devuelve un ResponseEntity de una vez y no un DatosDetallesTopioco

        var response = mockMvc.perform(post("/topicos/registrar").contentType(MediaType.APPLICATION_JSON).content(registrarTopicoJacksonTester.write(new DatosRegistrarTopico(1L, 1L, titulo, mensaje)).getJson()) // Daba error porque un
                // objeto Tipo Objet se debe pasar a tipo json para eso JacksonTester
        ).andReturn().getResponse();

        // Then
        // Verificar que el estado de esta requision sea del estado 400, BAD_REQUEST es igual al estado 400
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = datosDetallesTopicoJacksonTester.write(datos).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


    @Test
    @DisplayName("Debe retornar estado http 404 Not found cuando cuando se busque algo que no existe" + "en la base datos")
    void debeRetornarError404Escenario3() throws Exception {
        // Configura MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(topicoController).build();

        // Define el ID inválido
        Long invalidoId = 9999L;

        // Simula una solicitud GET al endpoint
        mockMvc.perform(get("/topicos/listar/{id}", invalidoId))
                // Verifica que la respuesta tenga el estado HTTP 404
                .andExpect(status().isNotFound());
    }

}
