package br.com.lulabs.agendamento.controller;

import br.com.lulabs.agendamento.entity.Agendamento;
import br.com.lulabs.agendamento.service.AgendamentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class AgendamentoController {

    @Autowired
    AgendamentoService service;

    @ApiOperation(value = "Cria um agendamento para envio de mensagens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento criado!"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/agendamentos")
    public ResponseEntity<Agendamento> salvar(@RequestBody @Valid Agendamento agendamento) {
        return new ResponseEntity<Agendamento>(service.salvar(agendamento), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retorna a lista de agendamentos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de agendamentos"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamentos")
    private ResponseEntity<List<Agendamento>> consultar() {
        List<Agendamento> consultas = service.buscar();
        if (consultas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Agendamento>>(consultas, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Retorna um agendamento através de um ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um agendamento através de um ID"),
            @ApiResponse(code = 204, message = "Conteúdo não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/agendamentos/{id}")
    private ResponseEntity<Agendamento> consultaPorId(@PathVariable(value = "id") long id) {
        Optional<Agendamento> consulta = service.buscarPorId(id);
        if (consulta.isPresent()) {
            return new ResponseEntity<Agendamento>(consulta.get(), HttpStatus.OK);
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
    private ResponseEntity<Agendamento> remover(@PathVariable(value = "id") long id) {
        return service.remover(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
