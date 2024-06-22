package med.voll.vollapirest.domain.consulta.validations.agendar;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicoSinConsulta implements AgendarConsultasValidator {

    private final ConsultaRepository consultaRepository;

    public MedicoSinConsulta(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }


    public void validar(AgendarConsultaDTO datos) {
        if (datos.idMedico() == null) {
            return;
        }
        boolean medicoConConsulta = consultaRepository.existsByMedico_IdAndFecha(
                datos.idMedico(), datos.fecha());

        if (medicoConConsulta) {
            throw new ValidationException(
                    "Este médico ya tiene una consulta en el horario proporcionado");
        }
    }
}
