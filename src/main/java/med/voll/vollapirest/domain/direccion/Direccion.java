package med.voll.vollapirest.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DireccionDTO datosDireccion) {
        try {
            this.calle = datosDireccion.calle();
            this.distrito = datosDireccion.distrito();
            this.ciudad = datosDireccion.ciudad();
            this.numero = datosDireccion.numero();
            this.complemento = datosDireccion.complemento();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Direccion actualizarDatos(DireccionDTO datosDireccion) {
        this.calle = datosDireccion.calle();
        this.distrito = datosDireccion.distrito();
        this.ciudad = datosDireccion.ciudad();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        return this;
    }
}
