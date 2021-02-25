package br.com.lulabs.agendamento.service;

import br.com.lulabs.agendamento.entity.Agendamento;

import java.util.List;
import java.util.Optional;

public interface AgendamentoService {

    Agendamento salvar(Agendamento agendamento);
    List<Agendamento> buscar();
    Optional<Agendamento> buscarPorId(Long id);
    boolean remover(Long id);
}
