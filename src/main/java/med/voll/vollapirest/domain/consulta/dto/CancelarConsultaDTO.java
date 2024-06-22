package med.voll.vollapirest.domain.consulta.dto;

import jakarta.validation.constraints.NotBlank;
import med.voll.vollapirest.domain.consulta.MotivoCancelacion;

public record CancelarConsultaDTO(
        @NotBlank
        Long idConsulta,

        @NotBlank
        MotivoCancelacion motivo
) {
}
