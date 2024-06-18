package med.voll.vollapirest.domain.consulta.service;

import med.voll.vollapirest.domain.consulta.Consulta;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.medico.Medico;
import med.voll.vollapirest.domain.medico.MedicoRepository;
import med.voll.vollapirest.domain.paciente.Paciente;
import med.voll.vollapirest.domain.paciente.PacienteRepository;
import med.voll.vollapirest.infra.errors.exceptions.ValidacionDatosAgendaException;
import org.springframework.stereotype.Service;


@Service
public class AgendarConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AgendarConsultaService(ConsultaRepository consultaRepository,
                                  MedicoRepository medicoRepository,
                                  PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }


    public void agendar(AgendarConsultaDTO datosAgenda) {
        if (pacienteRepository.findById(datosAgenda.idPaciente()).isEmpty()) {
            throw new ValidacionDatosAgendaException("El ID proporcionado para el paciente no fue encontrado");
        }
        if (datosAgenda.idMedico() != null && !medicoRepository.existsById(datosAgenda.idMedico())) {
            throw new ValidacionDatosAgendaException("El ID proporcionado para el medico no fue encontrado");
        }

        Paciente paciente = pacienteRepository.findById(datosAgenda.idPaciente()).get();
        Medico medico = seleccionarMedico(datosAgenda);

        Consulta consulta = new Consulta(null, medico, paciente, datosAgenda.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(AgendarConsultaDTO datosAgenda) {
        if (datosAgenda.idMedico() != null) {
            return medicoRepository.getReferenceById(datosAgenda.idMedico());
        }

        if (datosAgenda.especialidad() == null) {
            throw new ValidacionDatosAgendaException("Debe seleccionarse una especialidad para el médico");
        }

        return medicoRepository.seleccionarMedicoPorEspecialidadEnFecha(
                datosAgenda.especialidad(), datosAgenda.fecha());
    }
}
