package med.voll.vollapirest.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ConsultasValidator {

    private final ConsultaRepository consultaRepository;

    public PacienteSinConsulta(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }


    public void validar(AgendarConsultaDTO datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        boolean pacienteConConsulta = consultaRepository.existsByPaciente_IdAndFechaBetween(
                datos.idPaciente(), primerHorario, ultimoHorario);

        if (pacienteConConsulta) {
            throw new ValidationException(
                    "Este paciente ya tiene una consulta programada para el día proporcionado");
        }
    }
}
