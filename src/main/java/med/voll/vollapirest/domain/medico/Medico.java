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

    public Medico(RegistroMedicoDTO registroMedico) {
        this.nombre = registroMedico.nombre();
        this.email = registroMedico.email();
        this.documento = registroMedico.documento();
        this.especialidad = registroMedico.especialidad();
        this.direccion = new Direccion(registroMedico.direccion());
        this.telefono = registroMedico.telefono();
        this.activo = true;
    }

    public void actualizarDatos(ActualizarMedicoDTO datosMedico) {
        this.nombre = datosMedico.nombre() != null
                ? datosMedico.nombre()
                : this.nombre;
        this.documento = datosMedico.documento() != null
                ? datosMedico.documento()
                : this.documento;
        this.telefono = datosMedico.telefono() != null
                ? datosMedico.telefono()
                : this.telefono;
        this.direccion = datosMedico.direccion() != null
                ? direccion.actualizarDatos(datosMedico.direccion())
                : this.direccion;
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
