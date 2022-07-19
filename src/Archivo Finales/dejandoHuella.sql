/* PROYECTO WEB - DEJANDO HUELLA */
DROP DATABASE IF EXISTS dejandoHuella;
CREATE DATABASE dejandoHuella;
USE dejandoHuella;

/* Tabla ROL (Donde encontraremos a los diferentes Usuarios) */
CREATE TABLE rol
(
	id_rol BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	usuario VARCHAR(25) NOT NULL UNIQUE,
	password VARCHAR(16) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	nombre VARCHAR(25) NOT NULL,
	role ENUM ('ROLE_ADMIN', 'ROLE_CENTRO', 'ROLE_USER') NOT NULL,
	activo TINYINT(1) NOT NULL
);

/* Tabla ADMINISTRADOR (es hijo de la tabla ROL) */
CREATE TABLE administrador
(
	id_administrador BIGINT(20) PRIMARY KEY,
	apellido1 VARCHAR(50) NOT NULL,
	apellido2 VARCHAR(50) DEFAULT NULL,
	FOREIGN KEY (id_administrador) REFERENCES rol(id_rol) ON UPDATE CASCADE ON DELETE CASCADE
);

/* Tabla CENTRO ADOPCION (es hijo de la tabla ROL) */
CREATE TABLE centro_adopcion
(
	id_centro BIGINT(20) PRIMARY KEY,
	nif VARCHAR(9) NOT NULL UNIQUE,
	telefono VARCHAR(9) NOT NULL,
	ciudad VARCHAR(25) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	FOREIGN KEY (id_centro) REFERENCES rol(id_rol) ON UPDATE CASCADE ON DELETE CASCADE
);

/* Tabla USUARIO (es hijo de la tabla ROL) */
CREATE TABLE usuario
(
	id_usuario BIGINT(20) PRIMARY KEY,
	dni VARCHAR(9) NOT NULL UNIQUE,
	apellido1 VARCHAR(50) NOT NULL,
	apellido2 VARCHAR(50) DEFAULT NULL,
	fecha_nacimiento DATE NOT NULL,
	telefono VARCHAR(9) NOT NULL,
	ciudad VARCHAR(25) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES rol(id_rol) ON UPDATE CASCADE ON DELETE CASCADE
);

/* Tabla ANIMAL (Donde guardaremos los diferentes animales) */
CREATE TABLE animal
(
	id_animal BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(25) NOT NULL,
	raza VARCHAR(25) NOT NULL,
	observaciones VARCHAR(500) NOT NULL,
	adoptado TINYINT(1) NOT NULL,
	foto MEDIUMBLOB NOT NULL
);

/* Tabla ANIMAL CENTRO ADOPCION (es hijo de la tabla ANIMAL) */
CREATE TABLE animal_centro
(
	id_animal_centro BIGINT(20) PRIMARY KEY,
	historia VARCHAR(300) NOT NULL,
	caracter VARCHAR(100) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	tamano ENUM ('Pequeño', 'Mediano', 'Grande') NOT NULL,
	peso INTEGER(2) NOT NULL,
	costes_adopcion INTEGER(4) NOT NULL DEFAULT 0,
	id_centro BIGINT(20),
	FOREIGN KEY (id_animal_centro) REFERENCES animal(id_animal) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_centro) REFERENCES centro_adopcion(id_centro) ON UPDATE CASCADE ON DELETE CASCADE
);

/* Tabla ANIMAL PERDIDO (es hijo de la tabla ANIMAL) */
CREATE TABLE animal_perdido
(
	id_animal_perdido BIGINT(20) PRIMARY KEY,
	ciudad VARCHAR(25) NOT NULL,
	id_usuario BIGINT(20),
	FOREIGN KEY (id_animal_perdido) REFERENCES animal(id_animal) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE
);

/* Tabla ADOPTAN (es la unión de la tabla USUARIO con ANIMAL CENTRO ADOPCION) */
CREATE TABLE adoptan
(
	id_usuario BIGINT(20),
	id_animal_centro BIGINT(20),
	fecha_adopcion DATE NOT NULL,
	PRIMARY KEY (id_usuario, id_animal_centro),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_animal_centro) REFERENCES animal_centro(id_animal_centro) ON UPDATE CASCADE ON DELETE CASCADE
);