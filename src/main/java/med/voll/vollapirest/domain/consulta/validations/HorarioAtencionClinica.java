package med.voll.vollapirest.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioAtencionClinica implements ConsultasValidator {
    public void validar(AgendarConsultaDTO datos) {
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour() < 7;
        var despuesDeCierre = datos.fecha().getHour() > 19;

        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException(
                    "El horario de atención de la clínica es de 7:00 a 19:00 y de lunes a sábado");
        }
    }
}
