package br.com.lulabs.agendamento.controller;

import br.com.lulabs.agendamento.entity.AgendamentoEntity;
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
@RequestMapping(path = "/v1")
public class AgendamentoController {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @ApiOperation(value = "Cria um agendamento para envio de mensagens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento criado!"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/agendamentos")
    public ResponseEntity<AgendamentoEntity> salvar(@RequestBody @Valid AgendamentoEntity agendamentoEntity) {
        agendamentoEntity.setDataCriacao(LocalDateTime.now());
        return new ResponseEntity<AgendamentoEntity>(agendamentoRepository.save(agendamentoEntity), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna a lista de agendamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de agendamentos"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamentos")
    private ResponseEntity<List<AgendamentoEntity>> consultar() {
        List<AgendamentoEntity> consultas = agendamentoRepository.findAll();
        if (consultas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<AgendamentoEntity>>(consultas, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Retorna um agendamento através de um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um agendamento através de um ID"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamentos/{id}")
    private ResponseEntity<AgendamentoEntity> consultaPorId(@PathVariable(value = "id") long id) {
        Optional<AgendamentoEntity> consulta = agendamentoRepository.findById(id);
        if (consulta.isPresent()) {
            return new ResponseEntity<AgendamentoEntity>(consulta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Remove um agendamento através de um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento removido"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/agendamentos/{id}")
    private ResponseEntity<AgendamentoEntity> remover(@PathVariable(value = "id") long id) {
        Optional<AgendamentoEntity> agendamento = agendamentoRepository.findById(id);
        if (agendamento.isPresent()) {
            agendamentoRepository.delete(agendamento.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
