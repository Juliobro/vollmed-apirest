package med.voll.vollapirest.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;

public record ActualizarPacienteDTO(

        @NotNull(message = "Debes indicar el ID del paciente a modificar")
        Long id,

        String nombre,
        String telefono,

        @Valid
        DireccionDTO direccion
) {
}