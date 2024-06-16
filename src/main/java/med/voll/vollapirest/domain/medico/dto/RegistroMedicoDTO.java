package med.voll.vollapirest.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;
import med.voll.vollapirest.domain.medico.Especialidad;

public record RegistroMedicoDTO(

        //El message es para personalizar el mensaje cuando no se cumple la anotacion
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Email es obligatorio")
        @Email
        String email,

        @NotBlank(message = "Teléfono es obligatorio")
        String telefono,

        @NotBlank(message = "Documento es obligatorio")
        @Pattern(regexp = "\\d{4,6}", message = "Documento debe ser entre 4 y 6 numeros")
        String documento,

        @NotNull(message = "Espcialidad es obligatoria")
        Especialidad especialidad,

        @NotNull(message = "Direccion es obligatoria")
        @Valid //Lo que hace valid es indicar que verifique las condiciones especificadas dentro de la clase
        DireccionDTO direccion
) {
}
