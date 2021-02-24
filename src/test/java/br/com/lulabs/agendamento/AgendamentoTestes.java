package br.com.lulabs.agendamento;

import br.com.lulabs.agendamento.entity.AgendamentoEntity;
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
        LocalDateTime dataEnvio = LocalDateTime.now().plusDays(1);
        AgendamentoEntity agendamentoEntity = new AgendamentoEntity(999999L, dataCriacao, "Tief", "Mensagem de teste", dataEnvio,false, "SMS");
        AgendamentoEntity agendamentoSalvo = agendamentoRepository.save(agendamentoEntity);
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
        Optional<AgendamentoEntity> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            agendamentoRepository.delete(agendamentoResponse.get());
        }
    }
}
