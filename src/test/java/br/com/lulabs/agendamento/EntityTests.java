package br.com.lulabs.agendamento;

import br.com.lulabs.agendamento.entity.Agendamento;
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
public class EntityTests {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Test
    @Rollback(false)
    public void testeAgendamento() {
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataEnvio = LocalDateTime.now().plusDays(1);
        Agendamento agendamento = new Agendamento(999999L, dataCriacao, "Tief", "Mensagem de teste", dataEnvio,false, "SMS");
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
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
        Optional<Agendamento> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            agendamentoRepository.delete(agendamentoResponse.get());
        }
    }
}
