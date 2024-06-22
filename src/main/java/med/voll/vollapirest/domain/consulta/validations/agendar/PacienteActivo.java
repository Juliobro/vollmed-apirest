package med.voll.vollapirest.domain.consulta.validations.agendar;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements AgendarConsultasValidator {

    private final PacienteRepository pacienteRepository;

    public PacienteActivo(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public void validar(AgendarConsultaDTO datos) {
        boolean pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteActivo) {
            throw new ValidationException("El paciente especificado está inactivo");
        }
    }
}
