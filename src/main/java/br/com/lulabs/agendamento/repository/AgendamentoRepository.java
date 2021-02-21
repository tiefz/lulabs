package br.com.lulabs.agendamento.repository;

import br.com.lulabs.agendamento.model.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long> {
}
