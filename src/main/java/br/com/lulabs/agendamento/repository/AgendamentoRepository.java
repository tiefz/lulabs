package br.com.lulabs.agendamento.repository;

import br.com.lulabs.agendamento.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
