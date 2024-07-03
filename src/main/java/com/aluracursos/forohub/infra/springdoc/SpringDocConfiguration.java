package com.aluracursos.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class SpringDocConfiguration {



    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().
                                         type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Api  Foro Hub")
                        .description("Api Rest de la aplicación foro hub contiene las" +
                                " funcionalidades de CRUD de topico ")
                        .contact(new Contact()
                                .name("Equipo backend")
                                .email("backend.forhub@.email.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forohub/api/licencia")))
                .tags(
                        List.of(
                                new Tag().name("Autenticacion")
                                        .description("Endpoints relacionados con la autenticación"),
                               new Tag().name("Topico")
                                       .description("Endpoints relacionados con topicos")

                        ));


    }


}
