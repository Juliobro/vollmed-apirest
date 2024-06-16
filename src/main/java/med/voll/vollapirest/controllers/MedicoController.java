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
    @Transactional
    public ResponseEntity<DetallesMedicoDTO> registrarMedico(@RequestBody @Valid RegistroMedicoDTO datosMedico,
                                                             UriComponentsBuilder ucb) {
        Medico medico = medicoRepository.save(new Medico(datosMedico));
        URI url = ucb.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(new DetallesMedicoDTO(medico));
    }

                  //El parámetro Pageable puedes verlo como la configuración de la Page. Se modifica por medio de
    @GetMapping//los @PageableDefault o por medio de query params. Establece la configuración de la Page.
    public ResponseEntity<Page<ListadoMedicoDTO>> listadoMedicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(ListadoMedicoDTO::new));
    }

    @PutMapping //Esta anotación abre una transacción en la BD y cuando termina el método (sin errores)
    @Transactional //hace un "commit" de los cambios realizados a la BD. La transacción se libera al terminar.
    public ResponseEntity<DetallesMedicoDTO> actualizarMedico(@RequestBody @Valid ActualizarMedicoDTO datosMedico) {
        Medico medico = medicoRepository.getReferenceById(datosMedico.id());
        medico.actualizarDatos(datosMedico);
        return ResponseEntity.ok(new DetallesMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesMedicoDTO> retornarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetallesMedicoDTO(medico));
    }
}
