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

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendarConsultaService agendarService;

    public ConsultaController(AgendarConsultaService agendarService) {
        this.agendarService = agendarService;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DetallesConsultaDTO> agendarConsulta(@RequestBody @Valid AgendarConsultaDTO datos) {
        agendarService.agendar(datos);
        return ResponseEntity.ok(new DetallesConsultaDTO(
                datos.id(), datos.idMedico(), datos.idPaciente(), datos.fecha()));
    }
}
