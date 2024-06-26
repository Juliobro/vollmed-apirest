package med.voll.vollapirest.domain.medico;

import med.voll.vollapirest.domain.consulta.Consulta;
import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;
import med.voll.vollapirest.domain.medico.dto.RegistroMedicoDTO;
import med.voll.vollapirest.domain.paciente.Paciente;
import med.voll.vollapirest.domain.paciente.dto.RegistroPacienteDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Indica que no se debe reemplazar la BD dada por una en memoria
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Retorna Null cuando el Medico esté en consulta con otro Paciente en dicho horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {

        //Given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico();
        var paciente = registrarPaciente();
        registrarConsulta(medico,paciente,proximoLunes10H);

        //When
        var medicoLibre = medicoRepository.seleccionarMedicoPorEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        //Then
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Retorna un Médico aleatorio cuando se realice la consulta en la base de datos en dicho horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {

        //Given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico=registrarMedico();

        //When
        var medicoLibre = medicoRepository.seleccionarMedicoPorEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        //Then
        assertThat(medicoLibre).isEqualTo(medico);
    }


    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(medico, paciente, fecha));
    }

    private Medico registrarMedico() {
        var medico = new Medico(datosMedico());
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente() {
        var paciente = new Paciente(datosPaciente());
        em.persist(paciente);
        return paciente;
    }

    private RegistroMedicoDTO datosMedico() {
        return new RegistroMedicoDTO(
                "Jose",
                "j@mail.com",
                "61999999999",
                "123456",
                Especialidad.CARDIOLOGIA,
                datosDireccion()
        );
    }

    private RegistroPacienteDTO datosPaciente() {
        return new RegistroPacienteDTO(
                "Antonio",
                "a@mail.com",
                "61999999999",
                "654321",
                datosDireccion()
        );
    }

    private DireccionDTO datosDireccion() {
        return new DireccionDTO(
                "Loca",
                "Azul",
                "Acapulpo",
                "321",
                "12"
        );
    }
}