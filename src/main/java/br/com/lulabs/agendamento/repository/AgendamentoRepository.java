package br.com.lulabs.agendamento.repository;

import br.com.lulabs.agendamento.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {
}
