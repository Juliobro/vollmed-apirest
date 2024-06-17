package med.voll.vollapirest.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.vollapirest.domain.direccion.Direccion;
import med.voll.vollapirest.domain.paciente.dto.ActualizarPacienteDTO;
import med.voll.vollapirest.domain.paciente.dto.RegistroPacienteDTO;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Direccion direccion;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;

    public Paciente(RegistroPacienteDTO datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento = datos.documento();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());
        this.activo = true;
    }

    public void actualizarDatos(ActualizarPacienteDTO datos) {
        this.nombre = datos.nombre() != null
                ? datos.nombre()
                : this.nombre;
        this.telefono = datos.telefono() != null
                ? datos.telefono()
                : this.telefono;
        this.direccion = datos.direccion() != null
                ? direccion.actualizarDatos(datos.direccion())
                : this.direccion;
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}