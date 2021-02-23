[![GitHub release](https://img.shields.io/github/release/tiefz/lulabs.svg)](https://GitHub.com/tiefz/lulabs/releases/)

[![GitHub issues](https://img.shields.io/github/issues/tiefz/calculadhora.svg)](https://GitHub.com/tiefz/lulabs/issues/) [![GitHub issues-closed](https://img.shields.io/github/issues-closed/tiefz/lulabs.svg)](https://GitHub.com/tiefz/lulabs/issues?q=is%3Aissue+is%3Aclosed)


# Luizalabs Agendamento
### Desafio Técnico Luizalabs Agendamento de Mensagens
API RESTful Spring boot para agendamento de envio de mensagens, com banco de dados PostgreSQL

Objetivo do desafio é criar uma API RESTful com persistência em banco de dados, com um endpoint para criação do agendamento de envio de mensagem e outro para consulta de status do envio e remoção do agendamento.

Para implementação da API, estou utilizando Spring Boot versão 2.4.3 e para banco de dados utilizei uma imagem Docker de banco PostgreSQL versão 9.6.21-alpine.

Banco PostgreSQL

Instalação e testes:

Documentação da API em Swagger:
    http://localhost:8080//swagger-ui.html#/agendamento-controller

Projeto e Kanban:
    https://github.com/tiefz/lulabs/projects/1

#### Versão 1.0.0
- Criação do projeto Spring Boot, configuração de conexão do banco de dados PostgreSQL e implementação da classe de modelo de entidade

#### Versão 1.0.2
- Criação de interface extendendo JPARepository para persistência de dados

#### Versão 1.0.4
- Criação de classe controller, configuração do endpoint POST /agendamento que receberá o payload JSON para criação de agendamentos e costumização do Jackson para formato de datas

#### Versão 1.0.6
- Criação dos demais endpoints de consulta GET (Retorna todos os agendamentos), GET by ID (Retorna um agendamento pelo ID) e DELETE (Remove um agendamento)

#### Versão 1.0.8
- Criação de documentação Swagger com Springfox. Swagger UI em http://localhost:8080//swagger-ui.html#/agendamento-controller

#### Versão 1.0.10
- Implementação de classes de Testes com JUnit em testes > AgendamentoTestes