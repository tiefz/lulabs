package br.com.lulabs.agendamento.controller;

import br.com.lulabs.agendamento.model.AgendamentoModel;
import br.com.lulabs.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoModel> salvaAgendamento(@RequestBody @Valid AgendamentoModel agendamentoModel) {
        return new ResponseEntity<AgendamentoModel>(agendamentoRepository.save(agendamentoModel), HttpStatus.CREATED);
    }
}
