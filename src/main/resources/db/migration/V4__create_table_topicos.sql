CREATE TABLE topicos
(id int AUTO_INCREMENT NOT NULL,
titulo varchar(300),
mensaje varchar(4000),
fecha_de_creacion datetime,
status tinyint,
id_autor_topico int,
id_curso int,
PRIMARY KEY(id),
FOREIGN KEY(id_autor_topico)
REFERENCES usuarios(id),
FOREIGN KEY( id_curso)
REFERENCES cursos(id)
);