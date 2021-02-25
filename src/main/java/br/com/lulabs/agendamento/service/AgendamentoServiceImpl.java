package br.com.lulabs.agendamento.service;

import br.com.lulabs.agendamento.entity.Agendamento;
import br.com.lulabs.agendamento.repository.AgendamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service @Slf4j
public class AgendamentoServiceImpl implements AgendamentoService{

    @Autowired
    AgendamentoRepository repository;

    @Override
    public Agendamento salvar(Agendamento agendamento){
        if (Objects.nonNull(agendamento)) {
            agendamento.setDataCriacao(LocalDateTime.now());
            return repository.save(agendamento);
        } else {
            log.error("Não foi possível criar agendamento");
            return new Agendamento();
        }
    }

    @Override
    public List<Agendamento> buscar(){
        return repository.findAll();
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long id){
        return repository.findById(id);
    }

    @Override
    public boolean remover(Long id) {
         Optional<Agendamento> agendamento = repository.findById(id);
        if (agendamento.isPresent()) {
            repository.delete(agendamento.get());
            return true;
        }
        return false;
    }
}
