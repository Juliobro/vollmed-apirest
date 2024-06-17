package med.voll.vollapirest.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;


public record RegistroPacienteDTO(

        @NotBlank(message = "{validation.nombre}")
        String nombre,

        @NotBlank(message = "{validation.email}")
        @Email
        String email,

        @NotBlank(message = "{validation.telefono}")
        @Size(max = 15)
        String telefono,

        //@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        @NotBlank(message = "{validation.documento}")
        @Pattern(regexp = "\\d{4,6}", message = "{validation.documento.pattern}")
        String documento,

        @NotNull(message = "{validation.direccion}")
        @Valid
        DireccionDTO direccion
) {
}