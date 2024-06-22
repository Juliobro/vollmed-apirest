package med.voll.vollapirest.domain.consulta.validations.cancelar;

import med.voll.vollapirest.domain.consulta.dto.CancelarConsultaDTO;

public interface CancelarConsultasValidator {
    void validar(CancelarConsultaDTO datos);
}
