ALTER TABLE consultas ADD (
    motivo_cancelacion VARCHAR(50),
    activa TINYINT NOT NULL DEFAULT 1);

UPDATE consultas SET activa = 0;
