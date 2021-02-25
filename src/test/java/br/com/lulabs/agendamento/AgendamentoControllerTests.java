package br.com.lulabs.agendamento;

import br.com.lulabs.agendamento.controller.AgendamentoController;
import br.com.lulabs.agendamento.entity.Agendamento;
import br.com.lulabs.agendamento.service.AgendamentoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AgendamentoController.class)
@ContextConfiguration(classes = Agendamento.class)
public class AgendamentoControllerTests {

    @MockBean
    private AgendamentoServiceImpl service;
    @InjectMocks
    private AgendamentoController agendamentoController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(agendamentoController).build();
    }

    @Test
    @DisplayName("POST /agendamentos success")
    public void posTest() throws Exception{

        Agendamento model = agendamento();
        model.setId(999999L);

        when(service.salvar(model)).thenReturn(model);

        System.out.println(mapper.writeValueAsString(model));

        mockMvc.perform(post("/v1/agendamentos")
                .contentType("application/json")
                .content("{\"destinatario\":\"teste\",\"mensagem\":\"Mensagem teste\", \"plataforma\":\"SMS\",\"dataEnvio\":\"01-05-2021 00:00:00\"}")
                .accept("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getTest() throws Exception{
        Agendamento model = agendamento();
        model.setId(1L);

        List<Agendamento> models = new ArrayList<>();
        models.add(model);

        when(service.buscar()).thenReturn(models);

        mockMvc.perform(get("/v1/agendamentos/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(models.size())))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void getByIdTest() throws Exception{

        Agendamento model = agendamento();
        model.setId(999999L);

        when(service.buscarPorId(999999L)).thenReturn(Optional.of(model));

        mockMvc.perform(get("/v1/agendamentos/{id}", 999999L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(999999)));

    }

    @Test
    public void getByIdNoContentTest() throws Exception{
        mockMvc.perform(get("/v1/agendamentos/{id}", 2L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTest() throws Exception{
        when(service.remover(999999L)).thenReturn(true);

        mockMvc.perform(delete("/v1/agendamentos/{id}", 999999L))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNoContentTest() throws Exception{
        when(service.remover(2L)).thenReturn(true);

        mockMvc.perform(delete("/v1/agendamentos/{id}", 1L))
                .andExpect(status().isNoContent());
    }


    static Agendamento agendamento(){
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataEnvio = LocalDateTime.now().plusDays(1);

        return new Agendamento(null,
                dataCriacao,
                "Tief",
                "Mensagem de teste",
                dataEnvio,
                false,
                "SMS");

    }


}
