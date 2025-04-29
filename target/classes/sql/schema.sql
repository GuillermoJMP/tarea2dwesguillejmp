-- src/main/resources/sql/schema.sql

-- 0) Desactivar comprobaciones de FKs para poder borrar/crear en cualquier orden
SET FOREIGN_KEY_CHECKS = 0;

-- 1) Crear BD y seleccionarla
CREATE DATABASE IF NOT EXISTS `vivero`
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `vivero`;

-- 2) Borrar tablas existentes (en orden inverso a dependencias)
DROP TABLE IF EXISTS Mensaje;
DROP TABLE IF EXISTS Ejemplar;
DROP TABLE IF EXISTS Credenciales;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Planta;

-- 3) Crear tabla Planta (PK: codigo)
CREATE TABLE Planta (
  codigo            VARCHAR(10)   NOT NULL,
  nombre_comun      VARCHAR(100)  NOT NULL,
  nombre_cientifico VARCHAR(100)  NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) Crear tabla Persona (PK: id, correo único)
CREATE TABLE Persona (
  id     BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100)           NOT NULL,
  correo VARCHAR(150)           NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) Crear tabla Credenciales (PK: id; FK 1:1 a Persona)
CREATE TABLE Credenciales (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  usuario     VARCHAR(50)  NOT NULL UNIQUE,
  password    VARCHAR(255) NOT NULL,
  id_persona  BIGINT       NOT NULL UNIQUE,
  FOREIGN KEY (id_persona)
    REFERENCES Persona(id)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6) Crear tabla Ejemplar (PK: id; FK N:1 a Planta)
CREATE TABLE Ejemplar (
  id     BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL UNIQUE,
  codigo VARCHAR(10)  NOT NULL,
  FOREIGN KEY (codigo)
    REFERENCES Planta(codigo)
    ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7) Crear tabla Mensaje (PK: id; FK a Persona y a Ejemplar)
CREATE TABLE Mensaje (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  fechahora   DATETIME NOT NULL,
  mensaje     TEXT     NOT NULL,
  id_persona  BIGINT,
  id_ejemplar BIGINT   NOT NULL,
  FOREIGN KEY (id_persona)
    REFERENCES Persona(id)
    ON DELETE SET NULL,
  FOREIGN KEY (id_ejemplar)
    REFERENCES Ejemplar(id)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8) Crear admin para poder usar CU2 inmediatamente
INSERT INTO Persona (nombre, correo)
VALUES ('Administrador', 'admin@vivero.com');
SET @admin_id = LAST_INSERT_ID();
INSERT INTO Credenciales (usuario, password, id_persona)
VALUES ('admin', 'admin123', @admin_id);

-- 9) Reactivar comprobaciones de FKs
SET FOREIGN_KEY_CHECKS = 1;