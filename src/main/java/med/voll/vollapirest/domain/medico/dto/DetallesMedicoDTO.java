package med.voll.vollapirest.domain.medico.dto;

import med.voll.vollapirest.domain.direccion.dto.DireccionDTO;
import med.voll.vollapirest.domain.medico.Especialidad;
import med.voll.vollapirest.domain.medico.Medico;

public record DetallesMedicoDTO(
        Long id,
        String nombre,
        String email,
        Especialidad especialidad,
        String telefono,
        String documento,
        DireccionDTO direccion
) {
    public DetallesMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getEspecialidad(),
                medico.getTelefono(), medico.getDocumento(), new DireccionDTO(medico.getDireccion()));
    }
}
