package med.voll.vollapirest.controllers;

import med.voll.vollapirest.domain.consulta.dto.AgendarConsultaDTO;
import med.voll.vollapirest.domain.consulta.dto.DetallesConsultaDTO;
import med.voll.vollapirest.domain.consulta.service.ConsultaService;
import med.voll.vollapirest.domain.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AgendarConsultaDTO> agendarConsultaJacksonTester;

    @Autowired
    private JacksonTester<DetallesConsultaDTO> detalleConsultaJacksonTester;

    @MockBean
    private ConsultaService consultaService;


    @Test
    @DisplayName("Retorna HTTP 400 cuando los datos ingresados sean inválidos")
    @WithMockUser //Esta anotación hace simular que ya estás autenticado, por lo tanto no te pedirá JWT
    void agendarEscenario1() throws Exception {
        //Given //When
        var response = mvc.perform(post("/consultas"))
                .andReturn()
                .getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retorna HTTP 201 cuando los datos ingresados son válidos")
    @WithMockUser
    void agendarEscenario2() throws Exception {

        //Given
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datos = new DetallesConsultaDTO(null, 2L, 5L, fecha, true);

        //When
        when(consultaService.agendar(any())).thenReturn(datos);

        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(agendarConsultaJacksonTester.write(new AgendarConsultaDTO(
                        2L, 5L, fecha, especialidad)).getJson()))
                .andReturn()
                .getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = detalleConsultaJacksonTester.write(datos).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}