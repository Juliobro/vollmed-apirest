package med.voll.vollapirest.domain.consulta.dto;

import java.time.LocalDateTime;

public record DetallesConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime fecha
) {
}
