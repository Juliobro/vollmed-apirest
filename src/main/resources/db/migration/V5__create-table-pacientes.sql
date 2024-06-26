CREATE TABLE pacientes (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nombre VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         documento VARCHAR(6) NOT NULL UNIQUE,
                         telefono VARCHAR(15) NOT NULL,
                         activo TINYINT NOT NULL DEFAULT 1,
                         calle VARCHAR(100) NOT NULL,
                         distrito VARCHAR(100) NOT NULL,
                         complemento VARCHAR(100),
                         numero VARCHAR(20),
                         ciudad VARCHAR(100) NOT NULL,
                         PRIMARY KEY (id)
);