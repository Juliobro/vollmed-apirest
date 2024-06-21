package med.voll.vollapirest.domain.consulta.validations;

import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;

public interface ConsultasValidator {
    void validar(AgendarConsultaDTO datos);
}
