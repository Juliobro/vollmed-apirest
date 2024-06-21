package med.voll.vollapirest.domain.consulta.service;

import med.voll.vollapirest.domain.consulta.Consulta;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.consulta.dto.DetallesConsultaDTO;
import med.voll.vollapirest.domain.consulta.validations.ConsultasValidator;
import med.voll.vollapirest.domain.medico.Medico;
import med.voll.vollapirest.domain.medico.MedicoRepository;
import med.voll.vollapirest.domain.paciente.Paciente;
import med.voll.vollapirest.domain.paciente.PacienteRepository;
import med.voll.vollapirest.infra.errors.exceptions.ValidacionDeIntegridadException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgendarConsultaService {   //Se busca que cumpla con la inversión de depedencia

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ConsultasValidator> validadores;

    public AgendarConsultaService(ConsultaRepository consultaRepository,
                                  MedicoRepository medicoRepository,
                                  PacienteRepository pacienteRepository,
                                  List<ConsultasValidator> validadores) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadores = validadores;
    }


    public DetallesConsultaDTO agendar(AgendarConsultaDTO datosAgenda) {
        if (datosAgenda.idPaciente() == null) {
            throw new ValidacionDeIntegridadException("El ID del paciente no puede ser nulo");
        }
        if (pacienteRepository.findById(datosAgenda.idPaciente()).isEmpty()) {
            throw new ValidacionDeIntegridadException("El ID proporcionado para el paciente no fue encontrado");
        }
        if (datosAgenda.idMedico() != null && !medicoRepository.existsById(datosAgenda.idMedico())) {
            throw new ValidacionDeIntegridadException("El ID proporcionado para el médico no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datosAgenda));

        Paciente paciente = pacienteRepository.findById(datosAgenda.idPaciente()).get();
        Medico medico = seleccionarMedico(datosAgenda);
        if (medico == null) {
            throw new ValidacionDeIntegridadException(
                    "No hay médicos disponibles para esta especialidad en este horario");
        }

        Consulta consulta = new Consulta(null, medico, paciente, datosAgenda.fecha());
        consultaRepository.save(consulta);

        return new DetallesConsultaDTO(consulta);
    }

    private Medico seleccionarMedico(AgendarConsultaDTO datosAgenda) {
        if (datosAgenda.idMedico() != null) {
            return medicoRepository.getReferenceById(datosAgenda.idMedico());
        }

        if (datosAgenda.especialidad() == null) {
            throw new ValidacionDeIntegridadException("Debe seleccionarse una especialidad para el médico");
        }

        return medicoRepository.seleccionarMedicoPorEspecialidadEnFecha(
                datosAgenda.especialidad(), datosAgenda.fecha());
    }
}
