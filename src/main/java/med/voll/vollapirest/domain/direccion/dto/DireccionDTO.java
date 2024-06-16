package med.voll.vollapirest.domain.direccion.dto;

import jakarta.validation.constraints.NotBlank;
import med.voll.vollapirest.domain.direccion.Direccion;

public record DireccionDTO(

        //Aquí estoy obteniendo el contenido de mensaje desde el archivo .properties correspondiente
        @NotBlank(message = "{validation.calle}")
        String calle,

        @NotBlank(message = "{validation.distrito}")
        String distrito,

        @NotBlank(message = "{validation.ciudad}")
        String ciudad,

        @NotBlank(message = "{validation.numero}")
        String numero,

        @NotBlank(message = "{validation.complemento}")
        String complemento
) {
    public DireccionDTO(Direccion direccion) {
        this(direccion.getCalle(), direccion.getDistrito(), direccion.getCiudad(),
                direccion.getNumero(), direccion.getComplemento());
    }
}
