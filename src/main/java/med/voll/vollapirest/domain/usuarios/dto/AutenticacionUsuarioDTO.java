package med.voll.vollapirest.domain.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionUsuarioDTO(

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
