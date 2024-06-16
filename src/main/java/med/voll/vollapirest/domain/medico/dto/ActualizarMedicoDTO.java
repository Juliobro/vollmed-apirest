package med.voll.vollapirest.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;

public record ActualizarMedicoDTO(

        @NotNull(message = "Debes indicar el ID del médico a modificar")
        Long id,

        String nombre,
        String documento,
        String telefono,

        @Valid
        DireccionDTO direccion
) {
}
