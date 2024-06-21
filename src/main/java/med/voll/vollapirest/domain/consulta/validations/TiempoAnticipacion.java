package med.voll.vollapirest.domain.consulta.validations;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TiempoAnticipacion implements ConsultasValidator {
    public void validar(AgendarConsultaDTO datos) {
        var ahora = LocalDateTime.now();
        var horaConsulta = datos.fecha();
        var diferenciaEnMinutos = Duration.between(ahora, horaConsulta);

        if (diferenciaEnMinutos.toMinutes() < 30) {
            throw new ValidationException(
                    "Debes programar la consulta con al menos 30 minutos de anticipación");
        }
    }
}
