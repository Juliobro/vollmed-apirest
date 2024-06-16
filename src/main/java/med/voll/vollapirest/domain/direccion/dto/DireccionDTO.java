package med.voll.vollapirest.domain.direccion.dto;

import jakarta.validation.constraints.NotBlank;
import med.voll.vollapirest.domain.direccion.Direccion;

public record DireccionDTO(

        @NotBlank(message = "Calle es obligatoria")
        String calle,

        @NotBlank(message = "Distrito es obligatorio")
        String distrito,

        @NotBlank(message = "Ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "Número es obligatorio")
        String numero,

        @NotBlank(message = "Complemento es obligatorio")
        String complemento
) {
    public DireccionDTO(Direccion direccion) {
        this(direccion.getCalle(), direccion.getDistrito(), direccion.getCiudad(),
                direccion.getNumero(), direccion.getComplemento());
    }
}
