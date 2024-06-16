package med.voll.vollapirest.domain.medico.dto;

import med.voll.vollapirest.domain.medico.Medico;

public record ListadoMedicoDTO(
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public ListadoMedicoDTO(Medico m) {
        this(m.getNombre(), m.getEspecialidad().toString(), m.getDocumento(), m.getEmail());
    }
}
