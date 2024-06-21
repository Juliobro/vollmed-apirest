package med.voll.vollapirest.domain.consulta.dto;

import med.voll.vollapirest.domain.consulta.Consulta;

import java.time.LocalDateTime;

public record DetallesConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
    public DetallesConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),
                consulta.getPaciente().getId(), consulta.getFecha());
    }
}
