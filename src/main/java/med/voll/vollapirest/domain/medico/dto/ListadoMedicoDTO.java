package med.voll.vollapirest.domain.medico.dto;

import med.voll.vollapirest.domain.medico.Medico;

public record ListadoMedicoDTO(
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public ListadoMedicoDTO(Medico medico) {
        this(medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(), medico.getEmail());
    }
}
