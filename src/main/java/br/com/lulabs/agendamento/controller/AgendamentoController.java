package br.com.lulabs.agendamento.controller;

import br.com.lulabs.agendamento.model.AgendamentoModel;
import br.com.lulabs.agendamento.repository.AgendamentoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @ApiOperation(value = "Cria um agendamento para envio de mensagens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento criado!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoModel> salvaAgendamento(@RequestBody @Valid AgendamentoModel agendamentoModel) {
        agendamentoModel.setDataCriacao(LocalDateTime.now());
        return new ResponseEntity<AgendamentoModel>(agendamentoRepository.save(agendamentoModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna a lista de agendamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de agendamentos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamento/consulta")
    private ResponseEntity<List<AgendamentoModel>> consultaAgendamento() {
        List<AgendamentoModel> agendamentos = agendamentoRepository.findAll();
        if (agendamentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<AgendamentoModel>>(agendamentos, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Retorna um agendamento através de um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um agendamento através de um ID"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamento/consulta/{id}")
    private ResponseEntity<AgendamentoModel> consultaAgendamentoPorId(@PathVariable(value = "id") long id) {
        Optional<AgendamentoModel> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            return new ResponseEntity<AgendamentoModel>(agendamentoResponse.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Remove um agendamento através de um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento removido"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/agendamento/consulta/{id}")
    private ResponseEntity<AgendamentoModel> removeAgendamento(@PathVariable(value = "id") long id) {
        Optional<AgendamentoModel> agendamentoResponse = agendamentoRepository.findById(id);
        if (agendamentoResponse.isPresent()) {
            agendamentoRepository.delete(agendamentoResponse.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
