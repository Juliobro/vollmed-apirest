package med.voll.vollapirest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.vollapirest.domain.consulta.Consulta;
import med.voll.vollapirest.domain.consulta.ConsultaRepository;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.consulta.dto.CancelarConsultaDTO;
import med.voll.vollapirest.domain.consulta.dto.DetallesConsultaDTO;
import med.voll.vollapirest.domain.consulta.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consulta Controller", description = "Agendar, mostrar y cancelar consultas")
public class ConsultaController {

    private final ConsultaService service;
    private final ConsultaRepository consultaRepository;

    public ConsultaController(ConsultaService service, ConsultaRepository consultaRepository) {
        this.service = service;
        this.consultaRepository = consultaRepository;
    }


    @PostMapping
    @Transactional
    @Operation(summary = "Agendar una consulta", description = "Crea una nueva entidad de Consulta en la BD")
    public ResponseEntity<DetallesConsultaDTO> agendarConsulta(@RequestBody @Valid AgendarConsultaDTO datos,
                                                               UriComponentsBuilder ucb) {
        var consulta = service.agendar(datos);
        URI url = ucb.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();
        return ResponseEntity.created(url).body(consulta);
    }

    @DeleteMapping
    @Transactional
    @Operation(summary = "Cancelar una consulta", description = "Establece una consulta como inactiva en la BD")
    public ResponseEntity<Void> cancelarConsulta(@RequestBody @Valid CancelarConsultaDTO datos) {
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mostrar una consulta", description = "Muestra una consulta de la BD según el ID proporcionado")
    public ResponseEntity<DetallesConsultaDTO> mostrarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetallesConsultaDTO(consulta));
    }
}
