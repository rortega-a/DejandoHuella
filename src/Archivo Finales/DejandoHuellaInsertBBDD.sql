-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.4-MariaDB-1:10.6.4+maria~focal - mariadb.org binary distribution
-- SO del servidor:              debian-linux-gnu
-- HeidiSQL Versión:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para dejandoHuella
CREATE DATABASE IF NOT EXISTS `dejandoHuella` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `dejandoHuella`;

-- Volcando estructura para tabla dejandoHuella.administrador
CREATE TABLE IF NOT EXISTS `administrador` (
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `id_administrador` bigint(20) NOT NULL,
  PRIMARY KEY (`id_administrador`),
  CONSTRAINT `FKfqj96bg002hcaa72vh73lfuhy` FOREIGN KEY (`id_administrador`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.administrador: ~1 rows (aproximadamente)
INSERT INTO `administrador` (`apellido1`, `apellido2`, `id_administrador`) VALUES
	('Bazuelo', 'Naranjo', 1);

-- Volcando estructura para tabla dejandoHuella.adoptan
CREATE TABLE IF NOT EXISTS `adoptan` (
  `id_animal_centro` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `fecha_adopcion` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_animal_centro`,`id_usuario`),
  KEY `FKgld6f1t5b3m84lslf78gklxyc` (`id_usuario`),
  CONSTRAINT `FKbu8kluk21xh1xnqj1a8rqx4pk` FOREIGN KEY (`id_animal_centro`) REFERENCES `animal_centro` (`id_animal_centro`),
  CONSTRAINT `FKgld6f1t5b3m84lslf78gklxyc` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.adoptan: ~1 rows (aproximadamente)
INSERT INTO `adoptan` (`id_animal_centro`, `id_usuario`, `fecha_adopcion`) VALUES
	(24, 7, '09/05/2022');

-- Volcando estructura para tabla dejandoHuella.animal
CREATE TABLE IF NOT EXISTS `animal` (
  `id_animal` bigint(20) NOT NULL AUTO_INCREMENT,
  `adoptado` bit(1) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `observaciones` varchar(255) NOT NULL,
  `raza` varchar(255) NOT NULL,
  PRIMARY KEY (`id_animal`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.animal: ~9 rows (aproximadamente)
INSERT INTO `animal` (`id_animal`, `adoptado`, `foto`, `nombre`, `observaciones`, `raza`) VALUES
	(16, b'0', 'b65fd58eb29f40b08ecfdc3ff4bd8dff.jpg', 'Coco', 'Dando un paseo vio una gallina y salió corriendo detrás. No lo volvimos a ver.', 'Labrador'),
	(17, b'0', '4af32a937fab454bbcc89bbaf1187564.jpg', 'Rayo', 'Se nos escapó en el Parque María Luisa.', 'Cocker'),
	(18, b'0', 'ad0d60fa9f764127a88973e7f86133fb.jpg', 'Ligero', 'Ojo, se pone agresivo si lo acorralas.', 'Rottweiler'),
	(19, b'0', 'b9a5855eb6954ed0a9a5e26157847c2b.jpg', 'Panzeco', 'Se fue de pastoreo y aún estamos buscando.', 'Pastor Belga'),
	(20, b'0', 'f29b1e617a5c49ca9fd5eafee058fa87.jpg', 'Burlón', 'Sin observaciones.', 'Mestizo'),
	(21, b'0', '02c2fb82978d40c3be7bcc9aea236c3a.jpg', 'Ares', 'Vacunas, chip y desparasitado.', 'Mestizo'),
	(22, b'0', '6f385b7c0c114ac589d816caaba353fd.jpg', 'Choco', 'Sin observaciones.', 'Labrador'),
	(23, b'0', '836ea2bee3ad439388245334dcc7bfe2.jpg', 'Murphy', 'Ha sufrido una reciente operación.', 'Teckel'),
	(24, b'1', '990a3571ea80440e82fd807bc8a0b104.jpg', 'Kovi', 'Desparasitado, chips y vacunas.', 'Labrador');

-- Volcando estructura para tabla dejandoHuella.animal_centro
CREATE TABLE IF NOT EXISTS `animal_centro` (
  `caracter` varchar(255) NOT NULL,
  `costes_adopcion` int(11) NOT NULL,
  `fecha_nacimiento` varchar(255) NOT NULL DEFAULT '',
  `historia` varchar(255) NOT NULL,
  `peso` int(11) NOT NULL,
  `tamano` varchar(255) NOT NULL,
  `id_animal_centro` bigint(20) NOT NULL,
  `id_centro_adopcion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_animal_centro`),
  KEY `FKs2fy71d8jndy1fiu3n2wxc5xs` (`id_centro_adopcion`),
  CONSTRAINT `FKqido4o2lmtj9l1005w9yet1cl` FOREIGN KEY (`id_animal_centro`) REFERENCES `animal` (`id_animal`),
  CONSTRAINT `FKs2fy71d8jndy1fiu3n2wxc5xs` FOREIGN KEY (`id_centro_adopcion`) REFERENCES `centro_adopcion` (`id_centro_adopcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.animal_centro: ~5 rows (aproximadamente)
INSERT INTO `animal_centro` (`caracter`, `costes_adopcion`, `fecha_nacimiento`, `historia`, `peso`, `tamano`, `id_animal_centro`, `id_centro_adopcion`) VALUES
	('Simpático', 0, '12/05/2020', 'Se encontró abandonado en el campo.', 12, 'Pequeño', 20, 9),
	('Enérgico', 80, '04/03/2019', 'Se encontró abandonado en una casa en ruinas.', 18, 'Mediano', 21, 9),
	('Tranquilo', 0, '16/05/2012', 'Familia ya no puede mantenerlo, busca otra que se pueda hacer cargo.', 22, 'Mediano', 22, 10),
	('Tranquilo', 100, '07/08/2015', 'Está enfermo y necesita cuidados especiales.', 12, 'Pequeño', 23, 10),
	('Activos', 65, '05/04/2022', 'Recién nacido, buscando un hogar.', 4, 'Mediano', 24, 9);

-- Volcando estructura para tabla dejandoHuella.animal_perdido
CREATE TABLE IF NOT EXISTS `animal_perdido` (
  `ciudad` varchar(255) NOT NULL,
  `id_animal_perdido` bigint(20) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_animal_perdido`),
  KEY `FKp6rxddwgq7ub8ijdsxxlwkwvk` (`id_usuario`),
  CONSTRAINT `FKi9a5egawdaj19we68x48x8lej` FOREIGN KEY (`id_animal_perdido`) REFERENCES `animal` (`id_animal`),
  CONSTRAINT `FKp6rxddwgq7ub8ijdsxxlwkwvk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.animal_perdido: ~4 rows (aproximadamente)
INSERT INTO `animal_perdido` (`ciudad`, `id_animal_perdido`, `id_usuario`) VALUES
	('Sevilla', 16, 7),
	('Sevilla', 17, 7),
	('Madrid', 18, 29),
	('Madrid', 19, 29);

-- Volcando estructura para tabla dejandoHuella.centro_adopcion
CREATE TABLE IF NOT EXISTS `centro_adopcion` (
  `ciudad` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nif` varchar(9) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `id_centro_adopcion` bigint(20) NOT NULL,
  PRIMARY KEY (`id_centro_adopcion`),
  UNIQUE KEY `UK_mx2xvdx0am0nyh51jdw8pibvq` (`nif`),
  CONSTRAINT `FKdnuher5k6et9r8k6syfqgcvfs` FOREIGN KEY (`id_centro_adopcion`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.centro_adopcion: ~2 rows (aproximadamente)
INSERT INTO `centro_adopcion` (`ciudad`, `direccion`, `nif`, `telefono`, `id_centro_adopcion`) VALUES
	('Málaga', 'C/Tarara', '12345678D', '687321456', 9),
	('Sevilla', 'C/Lejos, 3', '12345687V', '621789456', 10);

-- Volcando estructura para tabla dejandoHuella.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `activo` tinyint(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `UK_hr8q4sr7c3hqyd21jd51cqyx7` (`email`),
  UNIQUE KEY `UK_mxw8nxcik5qm7ca04u2nhf2iv` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.rol: ~5 rows (aproximadamente)
INSERT INTO `rol` (`id_rol`, `activo`, `email`, `nombre`, `password`, `role`, `usuario`) VALUES
	(1, 1, 'admin@gmail.com', 'Carlos', '$2a$15$bozAuWd8j1oYwFL59kp2WOeWmfZaPGjrabaCscvSYeHTlzTsYCmI6', 'ROLE_ADMIN', 'admin'),
	(7, 1, 'roro@gmail.com', 'Rafael', '$2a$15$eGOvReLS.L7KXtliaKzCaeQXjJN.8E9jJKLF0xbxHQRoKAE.BqgYS', 'ROLE_USER', 'user'),
	(9, 1, 'rr@gmail.com', 'Caninos Dog', '$2a$15$pk2H3GKeUDuQQqmwBZFYNeI9Krlg9ACXrVRaJc1ZWANeI.lO2ZHda', 'ROLE_CENTRO', 'centro'),
	(10, 1, 'reserva@gmail.com', 'Reserva Dogs', '$2a$15$d1EywsQcakRSQYXg0dv6t.yHHltv0G6HKxpuQfypAtIuwr8tvs75S', 'ROLE_CENTRO', 'centro2'),
	(29, 1, 'user2@gmail.com', 'Jose', '$2a$15$O.H.IH3ARa7FFPJP6m8Ye.ui2b4a8GNccNzTD/OU9Fs5bEhHqWzb.', 'ROLE_USER', 'user2');

-- Volcando estructura para tabla dejandoHuella.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `fecha_nacimiento` varchar(255) NOT NULL DEFAULT '',
  `telefono` varchar(255) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_ma71x4n4tydibsd9qt0m71le7` (`dni`),
  CONSTRAINT `FKp42oaypppykvisbuhhq483nxa` FOREIGN KEY (`id_usuario`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla dejandoHuella.usuario: ~2 rows (aproximadamente)
INSERT INTO `usuario` (`apellido1`, `apellido2`, `ciudad`, `direccion`, `dni`, `fecha_nacimiento`, `telefono`, `id_usuario`) VALUES
	('Ortega', 'Armario', 'Sevilla', 'C/Pizarro, 3', '12345678F', '11/02/1990', '654321789', 7),
	('Vargas', 'Granado', 'Madrid', 'C/ Castel, 3', '32080455H', '23/07/1991', '632487544', 29);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
