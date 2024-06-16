package med.voll.vollapirest.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.vollapirest.domain.direccion.Direccion;
import med.voll.vollapirest.domain.medico.dto.ActualizarMedicoDTO;
import med.voll.vollapirest.domain.medico.dto.RegistroMedicoDTO;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;

    public Medico(RegistroMedicoDTO datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento = datos.documento();
        this.telefono = datos.telefono();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
        this.activo = true;
    }

    public void actualizarDatos(ActualizarMedicoDTO datos) {
        this.nombre = datos.nombre() != null
                ? datos.nombre()
                : this.nombre;
        this.documento = datos.documento() != null
                ? datos.documento()
                : this.documento;
        this.telefono = datos.telefono() != null
                ? datos.telefono()
                : this.telefono;
        this.direccion = datos.direccion() != null
                ? direccion.actualizarDatos(datos.direccion())
                : this.direccion;
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
