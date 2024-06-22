package med.voll.vollapirest.domain.consulta.validations.agendar;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TiempoAnticipacion implements AgendarConsultasValidator {
    public void validar(AgendarConsultaDTO datos) {
        var ahora = LocalDateTime.now();
        var horaConsulta = datos.fecha();
        var tiempoDiferencia = Duration.between(ahora, horaConsulta);

        if (tiempoDiferencia.toMinutes() < 30) {
            throw new ValidationException(
                    "Debes programar la consulta con al menos 30 minutos de anticipación");
        }
    }
}
