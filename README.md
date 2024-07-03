

# CHALLENGE API FORO HUB

API Foro Hub es un proyecto backend en Java con Spring Boot para implementar los  conocimientos
adquiridos en Aluara Latam gracias al patrocinio de Oracle One Next Education. Esta API    está
diseñada bajo el modelo REST y sirve como foro donde los usuarios pueden hacer preguntas  sobre
diversos temas educativos de programación. En nuestro proyecto, nos    enfocamos en   el   nicho
educativo para permitir a los estudiantes crear topicos y resolver sus dudas. Como programadores
backend en Java,    implementamos operaciones     CRUD (Crear, Leer, Actualizar, Eliminar)  para   
gestionar los topicos. Además, se han implementado medidas    de seguridad como la autenticación
de usuarios para el acceso y  el uso  de  encriptación  para datos sensibles,  como contraseñas,
que se almacenan   en  la  base  de    datos.  También  se  utiliza JSON Web Token (JWT) para la
autenticación y validación de cada proceso realizado    por un  usuario,  asegurando que  sea un
usuario auténtico con la autoridad necesaria para realizar la acción. Por ejemplo, un estudiante
puede tener permiso para crear un topico, pero no para eliminarlo de la base de datos.
Se ha utilizado SpringDoc para la documentación JSON y la interfaz de usuario de Swagger, la cual
también ha sido personalizada.


*****************************************************************

## CONFIGURACIÓN DEL ENTORNO DE TRABAJO

### Tecnologías Utilizadas:

1. Lenguaje de programación: Java versión 17
2. Gestor de dependencias:   Maven versión 4
3. Framework: Spring Boot    versión 3.3.0
4. Dependencias utilizadas:
     
    Observación las  descripciones de las dependencias fueron hechas por IA.

    Lombok: Es una biblioteca de Java que ayuda a reducir el código boilerplate, como getters, setters, constructores, etc.
    Permite generar automáticamente este código a través de anotaciones, lo que hace que el código sea más conciso y legible.

    Spring Web: Esta dependencia proporciona las herramientas necesarias para construir aplicaciones web con Spring MVC.
    Incluye funcionalidades como controladores, vistas, interceptores y otros componentes web.

    Spring Boot DevTools:Esta dependencia proporciona herramientas de desarrollo que mejoran la experiencia de
    desarrollo con Spring Boot. Incluye características como recarga automática de la aplicación, soporte para LiveReload, etc.

    Spring Data JPA:Esta dependencia facilita la implementación de repositorios basados en JPA (Java Persistence API).
    Proporciona una capa de abstracción sobre JPA, lo que simplifica la interacción con la base de datos.

    Flyway Migration:Es una herramienta de migración de bases de datos que permite gestionar los cambios en el esquema de
    la base de datos de manera controlada. Ayuda a mantener la base de datos sincronizada entre diferentes entornos
    (desarrollo, producción, etc.).

    MySQL Driver:Esta dependencia proporciona el driver JDBC (Java Database Connectivity) necesario para conectar la 
    aplicación Spring Boot a una base de datos MySQL.
      
    Validation: Esta dependencia proporciona un conjunto de anotaciones y validadores para validar los datos de 
    entrada en la aplicación. Permite definir reglas de validación a nivel de modelo y aplicarlas de manera declarativa.

    Spring Security: Es un framework que proporciona seguridad a las aplicaciones web Java/Java EE.
    Permite implementar mecanismos de autenticación, autorización, protección contra ataques y otras características de seguridad.

******************************************************

### Estado  del proyecto:
    El proyecto cumple con lo planteado por Alura para el challenge Foro Hub. En el futuro, se implementará un CRUD para 
    usuarios y la lógica de roles para estudiantes, maestros y administradores. También se implementará la lógica   para
    que cada tópico de un curso esté asociado a un maestro que pueda responder, proporcionando así un     mejor flujo al
    servicio.



***************************************************

### Licencia: Apache  0.2

**************************************************


## Documentación  creada con SpringDoc para la API Foro HUb:


### Documentación de json de la api


![documentacion json de la api](img/img1.png)

### Interfaz Swagger de la API

![Interfaz Swagger de la API](img/img2.png)


### Autenticaión de usuario para iniciar sesión

![Autenticaión d](img/img3.png)


### Token generado para autorización y validación para realizar procesos

![token ](img/img4.png)

### Topico CRUD cread, read, update y delete

![CRUD](img/img5.png)

### Cread Registrar topico nuevo

![registrar](img/img7.png)

### Read  Listar topicos

![listar](img/img8.png)

### Update Actualizar topico por id

![Actualizar](img/img6.png)

### Delete Eliminar topicos por id

![Eliminar](img/img9.png)