CREATE TABLE respuestas
(id int  AUTO_INCREMENT NOT NULL,
mensaje varchar(1500),
id_topico int,
fecha_de_creacion datetime,
id_autor_respuesta int,
solucion varchar(4000),
PRIMARY KEY(id),
FOREIGN KEY(id_topico)
REFERENCES topicos(id),
FOREIGN KEY(id_autor_respuesta)
REFERENCES usuarios(id)
);