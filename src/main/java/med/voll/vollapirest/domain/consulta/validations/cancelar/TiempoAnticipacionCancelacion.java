package med.voll.vollapirest.domain.consulta.validations.cancelar;

import jakarta.validation.ValidationException;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.CancelarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TiempoAnticipacionCancelacion implements CancelarConsultasValidator{

    private final ConsultaRepository consultaRepository;

    public TiempoAnticipacionCancelacion(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }


    public void validar(CancelarConsultaDTO datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var tiempoDiferencia = Duration.between(ahora, consulta.getFecha());

        if (tiempoDiferencia.toHours() < 24) {
            throw new ValidationException(
                    "Una consulta solo puede ser cancelada con 24 horas de anticipacion");
        }
    }
}
