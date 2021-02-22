package br.com.lulabs.agendamento.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity @Data
@Table(name = "AGENDAMENTO")
public class AgendamentoModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "")
    @Column(name= "data_criacao", nullable = false, columnDefinition = "DATE")
    private LocalDate dataCriacao;

    @NotBlank(message = "O nome do destinatário é obrigatório.")
    @Size(max = 100, message = "O nome do destinatário deve conter no máximo 100 caracteres.")
    @Column(name = "destinatario", nullable = false, length = 100)
    private String destinatario;

    @NotBlank(message = "O campo de mensagem não pode estar vazio.")
    @Size(max = 280, message = "O texto da mensagem deve conter no máximo 280 caracteres.")
    @Column(name = "mensagem", nullable = false, length = 280)
    private String mensagem;

    @NotNull
    @FutureOrPresent(message = "{FutureOrPresent.agendamento.dataEnvio}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "")
    @Column(name= "data_envio", nullable = false, columnDefinition = "DATE")
    private LocalDate dataEnvio;

    @Column(columnDefinition = "boolean default false")
    private Boolean enviado;

    @NotBlank(message = "É necessário definir uma plataforma de envio.")
    @Column(name = "plataforma", nullable = false)
    private String plataforma;
}
