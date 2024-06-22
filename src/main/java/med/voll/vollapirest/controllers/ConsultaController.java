package med.voll.vollapirest.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DetallesConsultaDTO> agendarConsulta(@RequestBody @Valid AgendarConsultaDTO datos,
                                                               UriComponentsBuilder ucb) {
        var consulta = service.agendar(datos);
        URI url = ucb.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();
        return ResponseEntity.created(url).body(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelarConsulta(@RequestBody @Valid CancelarConsultaDTO datos) {
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
