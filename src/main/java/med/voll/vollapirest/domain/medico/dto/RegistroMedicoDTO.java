package med.voll.vollapirest.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;
import med.voll.vollapirest.domain.medico.Especialidad;

public record RegistroMedicoDTO(

        @NotBlank(message = "{validation.nombre}")
        String nombre,

        @NotBlank(message = "{validation.email}")
        @Email
        String email,

        @NotBlank(message = "{validation.telefono}")
        String telefono,

        @NotBlank(message = "{validation.documento}")
        @Pattern(regexp = "\\d{4,6}", message = "{validation.documento.pattern}")
        String documento,

        @NotNull(message = "{validation.especialidad}")
        Especialidad especialidad,

        @NotNull(message = "{validation.direccion}")
        @Valid //Lo que hace valid es indicar que verifique las condiciones especificadas dentro de la clase
        DireccionDTO direccion
) {
}
