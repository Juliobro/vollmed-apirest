package med.voll.vollapirest.domain.consulta.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.vollapirest.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record AgendarConsultaDTO(
        Long id,
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime fecha,

        Especialidad especialidad
) {
}
