package med.voll.vollapirest.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.vollapirest.domain.medico.Medico;
import med.voll.vollapirest.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(name = "motivo_cancelacion")
    @Enumerated(EnumType.STRING)
    private MotivoCancelacion motivoCancelacion;

    private LocalDateTime fecha;
    private boolean activa;


    public Consulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.activa = true;
    }

    public void cancelar(MotivoCancelacion motivo) {
        this.motivoCancelacion = motivo;
        this.activa = false;
    }
}
