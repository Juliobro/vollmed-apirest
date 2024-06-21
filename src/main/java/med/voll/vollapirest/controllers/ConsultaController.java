package med.voll.vollapirest.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.consulta.dto.DetallesConsultaDTO;
import med.voll.vollapirest.domain.consulta.service.AgendarConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendarConsultaService agendarService;

    public ConsultaController(AgendarConsultaService agendarService) {
        this.agendarService = agendarService;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DetallesConsultaDTO> agendarConsulta(@RequestBody @Valid AgendarConsultaDTO datos,
                                                               UriComponentsBuilder ucb) {
        var consulta = agendarService.agendar(datos);
        URI url = ucb.path("/consultas/{id}").buildAndExpand(consulta.id()).toUri();
        return ResponseEntity.created(url).body(consulta);
    }
}
