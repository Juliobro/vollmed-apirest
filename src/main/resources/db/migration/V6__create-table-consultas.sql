CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    fecha DATETIME NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_consultas_medico_id FOREIGN KEY(medico_id) references medicos(id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY(paciente_id) references pacientes(id)
);