package med.voll.vollapirest.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.vollapirest.domain.paciente.Paciente;
import med.voll.vollapirest.domain.paciente.PacienteRepository;
import med.voll.vollapirest.domain.paciente.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository repository) {
        this.pacienteRepository = repository;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DetallesPacienteDTO> registrarPaciente(@RequestBody @Valid RegistroPacienteDTO datosPaciente,
                                                             UriComponentsBuilder ucb) {
        Paciente paciente = pacienteRepository.save(new Paciente(datosPaciente));
        URI url = ucb.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(new DetallesPacienteDTO(paciente));
    }

                  //El parámetro Pageable puedes verlo como la configuración de la Page. Se modifica por medio de
    @GetMapping//los @PageableDefault o por medio de query params. Establece la configuración de la Page.
    public ResponseEntity<Page<ListadoPacienteDTO>> listadoPacientes(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map(ListadoPacienteDTO::new));
    }

    @PutMapping //Esta anotación abre una transacción en la BD y cuando termina el método (sin errores)
    @Transactional //hace un "commit" de los cambios realizados a la BD. La transacción se libera al terminar.
    public ResponseEntity<DetallesPacienteDTO> actualizarPaciente(@RequestBody @Valid ActualizarPacienteDTO datosPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(datosPaciente.id());
        paciente.actualizarDatos(datosPaciente);
        return ResponseEntity.ok(new DetallesPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPacienteDTO> retornarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetallesPacienteDTO(paciente));
    }
}
