CREATE TABLE usuarios
(id int AUTO_INCREMENT NOT NULL,
nombre varchar(300),
email varchar(300),
contrasenia varchar(300),
id_roll_usuario int,
PRIMARY KEY(id),
FOREIGN KEY(id_roll_usuario)
REFERENCES perfiles(id)
);


-- es la llave primaria de la tabla
--    PRIMARY KEY(id),

--es la llave extragera  para referenicar un columna en otra tabla
--    FOREIGN KEY(id_roll_usuario)

-- es la tabla de la otra entity a la que hace referencia
--    REFERENCES perfiles(id)