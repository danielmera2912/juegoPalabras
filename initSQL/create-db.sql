-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema juegoPalabra
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema juegoPalabra
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `juegoPalabra` DEFAULT CHARACTER SET utf8 ;
USE `juegoPalabra` ;

-- -----------------------------------------------------
-- Table `juegoPalabra`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabra`.`equipo` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `nombre` VARCHAR(45) NOT NULL,
    `logo` VARCHAR(100) NOT NULL,
    `fecha_creacion` DATE NOT NULL,
    `fecha_modificacion` DATE NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabra`.`jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS juegoPalabra.jugador (
                                                    id INT NOT NULL AUTO_INCREMENT,
                                                    nombre VARCHAR(45) NOT NULL,
    clave VARCHAR(45) NOT NULL,
    avatar VARCHAR(100) NOT NULL,
    id_equipo INT NULL,
    rol VARCHAR(45) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_modificacion DATE NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_Jugador_Equipo1_idx (id_equipo ASC) VISIBLE,
    UNIQUE INDEX correo_UNIQUE (correo ASC) VISIBLE,
    CONSTRAINT fk_Jugador_Equipo1
    FOREIGN KEY (id_equipo)
    REFERENCES juegoPalabra.equipo (id)
    ON DELETE SET NULL
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabra`.`juego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabra`.`juego` (
                                                      `id` INT NOT NULL AUTO_INCREMENT,
                                                      `nombre` VARCHAR(45) NOT NULL,
    `intentos` TINYINT(2) NOT NULL,
    `dificultad` ENUM("FACIL", "NORMAL", "DIFICIL") NOT NULL,
    `instrucciones` VARCHAR(500) NOT NULL,
    `fecha_modificacion` DATE NOT NULL,
    `fecha_creacion` DATE NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegoPalabra`.`partida`
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `juegoPalabra`.`partida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `juegoPalabra`.`partida` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `jugador_id` INT NOT NULL,
                                                        `juego_id` INT NOT NULL,
                                                        `intentos` TINYINT(2) NOT NULL,
    `palabra` VARCHAR(45) NOT NULL,
    `puntos` INT NOT NULL,
    `fecha` DATE NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `jugador_juego` (`jugador_id`, `juego_id`),
    INDEX `fk_Jugador_has_Juego_Juego1_idx` (`juego_id` ASC) VISIBLE,
    INDEX `fk_Jugador_has_Juego_Jugador_idx` (`jugador_id` ASC) VISIBLE,
    CONSTRAINT `fk_Jugador_has_Juego_Jugador`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `juegoPalabra`.`jugador` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Jugador_has_Juego_Juego1`
    FOREIGN KEY (`juego_id`)
    REFERENCES `juegoPalabra`.`juego` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




-- Datos para la tabla equipo
INSERT INTO juegoPalabra.equipo (nombre, logo, fecha_creacion, fecha_modificacion) VALUES
                                                                                       ('Rojo', 'https://example.com/rojo_logo.png', '2022-01-01', '2022-01-02'),
                                                                                       ('Azul', 'https://example.com/azul_logo.png', '2022-01-01', '2022-01-02'),
                                                                                       ('Verde', 'https://example.com/verde_logo.png', '2022-01-01', '2022-01-02');

-- Datos para la tabla jugador
INSERT INTO juegoPalabra.jugador (nombre, clave, avatar, id_equipo, rol, correo, fecha_creacion, fecha_modificacion) VALUES
                                                                                                                         ('Jugador1', 'password1', 'https://example.com/avatar1.png', 1, 'user', 'jugador1@example.com', '2022-01-01', '2022-01-02'),
                                                                                                                         ('Jugador2', 'password2', 'https://example.com/avatar2.png', 1, 'user', 'jugador2@example.com', '2022-01-01', '2022-01-02'),
                                                                                                                         ('Jugador3', 'password3', 'https://example.com/avatar3.png', 2, 'user', 'jugador3@example.com', '2022-01-01', '2022-01-02'),
                                                                                                                         ('Jugador4', 'password4', 'https://example.com/avatar4.png', 2, 'admin', 'jugador4@example.com', '2022-01-01', '2022-01-02');

-- Datos para la tabla juego
INSERT INTO juegoPalabra.juego (nombre, intentos, dificultad, instrucciones, fecha_modificacion, fecha_creacion) VALUES
                                                                                                                     ('Juego1', 5, 'FACIL', 'Adivina la palabra con 5 intentos', '2022-01-01', '2022-01-02'),
                                                                                                                     ('Juego2', 3, 'NORMAL', 'Adivina la palabra con 3 intentos', '2022-01-01', '2022-01-02'),
                                                                                                                     ('Juego3', 1, 'DIFICIL', 'Adivina la palabra con 1 intento', '2022-01-01', '2022-01-02');

-- Datos para la tabla partida
INSERT INTO juegoPalabra.partida (jugador_id, juego_id, intentos, palabra, puntos, fecha) VALUES
                                                                                              (1, 1, 5, 'perro', 50, '2022-01-01'),
                                                                                              (1, 2, 3, 'gato', 70, '2022-01-01'),
                                                                                              (2, 1, 5, 'casa', 30, '2022-01-01'),
                                                                                              (3, 3, 1, 'helicóptero', 100, '2022-01-01');

-- Datos para la tabla juego

INSERT INTO juegoPalabra.juego (nombre, intentos, dificultad, instrucciones, fecha_modificacion, fecha_creacion)
VALUES
    ('Palabras Fáciles', 3, 'FACIL', 'Adivina las palabras fáciles', '2022-03-01', '2022-02-28'),
    ('Palabras Normales', 5, 'NORMAL', 'Adivina las palabras de dificultad normal', '2022-03-02', '2022-02-28'),
    ('Palabras Difíciles', 7, 'DIFICIL', 'Adivina las palabras difíciles', '2022-03-03', '2022-02-28');
