package med.voll.vollapirest.domain.paciente.dto;

import med.voll.vollapirest.domain.paciente.Paciente;

public record ListadoPacienteDTO(
        Long id,
        String nombre,
        String email,
        String documento
) {
    public ListadoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getDocumento());
    }
}
