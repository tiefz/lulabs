package br.com.lulabs.agendamento;

import br.com.lulabs.agendamento.model.AgendamentoModel;
import br.com.lulabs.agendamento.repository.AgendamentoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AgendamentoTestes {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Test
    @Rollback(false)
    public void testeAgendamento() {
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataEnvio = LocalDateTime.of(2021,12,01,00,00,00,000);
        AgendamentoModel agendamentoModel = new AgendamentoModel(999999L, dataCriacao, "Tief", "Mensagem de teste", dataEnvio,false, "SMS");
        AgendamentoModel agendamentoSalvo = agendamentoRepository.save(agendamentoModel);
        Assert.assertNotNull(agendamentoSalvo);
    }

    @Test
    public void testeBuscaAgendamentoPorId() {
        long id = 999999L;
        agendamentoRepository.findById(id);

    }

    @Test
    public void testeRemoveAgendamento() {
        long id = 999999L;
        Optional<AgendamentoModel> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            agendamentoRepository.delete(agendamentoResponse.get());
        }
    }
}
