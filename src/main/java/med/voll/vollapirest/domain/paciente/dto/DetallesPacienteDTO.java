package med.voll.vollapirest.domain.paciente.dto;

import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;
import med.voll.vollapirest.domain.paciente.Paciente;

public record DetallesPacienteDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        DireccionDTO direccion
) {
    public DetallesPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento(),
                paciente.getTelefono(), new DireccionDTO(paciente.getDireccion()));
    }
}