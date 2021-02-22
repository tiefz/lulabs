package br.com.lulabs.agendamento.controller;

import br.com.lulabs.agendamento.model.AgendamentoModel;
import br.com.lulabs.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoModel> salvaAgendamento(@RequestBody @Valid AgendamentoModel agendamentoModel) {
        agendamentoModel.setDataCriacao(LocalDateTime.now());
        return new ResponseEntity<AgendamentoModel>(agendamentoRepository.save(agendamentoModel), HttpStatus.CREATED);
    }

    @GetMapping("/agendamento/consulta")
    private ResponseEntity<List<AgendamentoModel>> getAllAgendamento() {
        List<AgendamentoModel> agendamentos = agendamentoRepository.findAll();
        if (agendamentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<AgendamentoModel>>(agendamentos, HttpStatus.OK);
        }
    }

    @GetMapping("/agendamento/consulta/{id}")
    private ResponseEntity<AgendamentoModel> getAgendamentoById(@PathVariable(value = "id") long id) {
        Optional<AgendamentoModel> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            return new ResponseEntity<AgendamentoModel>(agendamentoResponse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/agendamento/consulta/{id}")
    private ResponseEntity<AgendamentoModel> deleteProduct(@PathVariable(value = "id") long id) {
        Optional<AgendamentoModel> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            agendamentoRepository.delete(agendamentoResponse.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
