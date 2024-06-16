package med.voll.vollapirest.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.vollapirest.domain.medico.Medico;
import med.voll.vollapirest.domain.medico.MedicoRepository;
import med.voll.vollapirest.domain.medico.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository repository) {
        this.medicoRepository = repository;
    }


    @PostMapping
    public ResponseEntity<RespuestaMedicoDTO> registrarMedico(@RequestBody @Valid RegistroMedicoDTO json,
                                                              UriComponentsBuilder ucb) {
        Medico medico = medicoRepository.save(new Medico(json));
        RespuestaMedicoDTO respuesta = new RespuestaMedicoDTO(medico);
        URI url = ucb.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

                  //El parámetro Pageable puedes verlo como la configuración de la Page. Se modifica por medio de
    @GetMapping//los @PageableDefault o por medio de query params. Establece la configuración de la Page.
    public ResponseEntity<Page<ListadoMedicoDTO>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(ListadoMedicoDTO::new));
    }

    @PutMapping //Esta anotación abre una transacción en la BD y cuando termina el método (sin errores)
    @Transactional //hace un "commit" de los cambios realizados a la BD. La transacción se libera al terminar.
    public ResponseEntity<RespuestaMedicoDTO> actualizarMedico(@RequestBody @Valid ActualizarMedicoDTO datosMedico) {
        Medico medico = medicoRepository.getReferenceById(datosMedico.id());
        medico.actualizarDatos(datosMedico);
        return ResponseEntity.ok(new RespuestaMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaMedicoDTO> retornarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new RespuestaMedicoDTO(medico);
        return ResponseEntity.ok(datosMedico);
    }
}
