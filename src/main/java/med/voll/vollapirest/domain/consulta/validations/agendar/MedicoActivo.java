package med.voll.vollapirest.domain.consulta.validations.agendar;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.medico.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements AgendarConsultasValidator {

    private final MedicoRepository medicoRepository;

    public MedicoActivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }


    public void validar(AgendarConsultaDTO datos) {
        if (datos.idMedico() == null) {
            return;
        }

        boolean medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoActivo) {
            throw new ValidationException("El médico seleccionado está inactivo");
        }
    }
}
