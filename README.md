[![GitHub release](https://img.shields.io/github/release/tiefz/lulabs.svg)](https://GitHub.com/tiefz/lulabs/releases/)

[![GitHub issues](https://img.shields.io/github/issues/tiefz/calculadhora.svg)](https://GitHub.com/tiefz/lulabs/issues/) [![GitHub issues-closed](https://img.shields.io/github/issues-closed/tiefz/lulabs.svg)](https://GitHub.com/tiefz/lulabs/issues?q=is%3Aissue+is%3Aclosed)

[![Build Status](https://travis-ci.com/tiefz/lulabs.svg?branch=master)](https://travis-ci.com/tiefz/lulabs)

# Luizalabs Agendamento
## Desafio Técnico Luizalabs Agendamento de Mensagens
API RESTful Spring boot para agendamento de envio de mensagens, com banco de dados PostgreSQL

Objetivo do desafio é criar uma API RESTful com persistência em banco de dados, com um endpoint para criação do agendamento de envio de mensagem e outro para consulta de status do envio e remoção do agendamento.

Para implementação da API, estou utilizando Spring Boot versão 2.4.3 e para banco de dados utilizei uma imagem Docker de banco PostgreSQL versão 9.6.21-alpine.

Modelo do banco PostgreSQL:

Banco LULABS  |  Tabela agendamento
------------- | --------------------
id | BIGINT 
data_criacao | TIMESTAMP 
destinatario | VARCHAR 100
mensagem | VARCHAR 280
data_envio | TIMESTAMP
enviado | BOOLEAN
plataforma | VARCHAR 60

Para testes utilizei JUnit e Mockito com as classes AgendamentoEntityTests e AgendamentoControllerTests, para documentação utilizei o plugin Springfox.
Todo o controle de versionamento foi baseado no modelo Gitflow utilizando o Github e finalmente para CI/CD, utilizei o Travis integrado com o Heroku e Dockerhub para publicação da versão em container da aplicação. 






# Instalação e testes:

## Teste live no Heroku

### Método POST - Criação de agendamento:

Endpoint: https://lulabs.herokuapp.com/agendamentos
JSON: {"destinatario":"teste","mensagem":"Mensagem teste", "plataforma":"SMS","dataEnvio":"01-05-2021 00:00:00"}

curl -d '{"destinatario":"teste","mensagem":"Mensagem teste", "plataforma":"SMS","dataEnvio":"01-05-2021 00:00:00"}' -H 'Content-Type: application/json' https://lulabs.herokuapp.com/agendamentos

### Método GET - Consultar um agendamento por ID ou todos os agendamentos 

Endpoint: https://lulabs.herokuapp.com/agendamentos 
Endpoint: https://lulabs.herokuapp.com/agendamentos/{id}

### Método DELETE - Remover um agendamento

Endpoint: https://lulabs.herokuapp.com/agendamentos/{id}

### Documentação da API em Swagger:
http://localhost:8080//swagger-ui.html#/agendamento-controller
#### Heroku URL: https://lulabs.herokuapp.com/swagger-ui.html/agendamento-controller

### Projeto e Kanban:
https://github.com/tiefz/lulabs/projects/1


# Teste local
    Banco de dados PostgreSQL - 
    DB = lulabs 
    Usuario = postgres
    Senha = postgres

Se preferir, utilizar imagem do Docker: 
```
docker run --name lulabs-postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=lulabs -p 5432:5432 -d postgres:9.6.21-alpine
````

Clonar o projeto e para teste seguir os mesmos endpoints do exemplo acima
Para criação de agendamento, utilizar JSON no formato abaixo:

```
{
    "destinatario":"string",
    "mensagem":"string", 
    "plataforma":"string",
    "dataEnvio":"01-12-2021 00:00:00"
}
```

#### Teste local com Docker-compose

arquivo docker-composer.yml

```
version: '2'

services:
  app:
    image: 'tiefz/lulabs:latest'
    build:
      context: .
    container_name: lulabs
    ports:
      - 8080:8080
    depends_on:
      - db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/compose-postgres
      - SPRING_DATASOURCE_USERNAME=compose-postgres
      - SPRING_DATASOURCE_PASSWORD=compose-postgres
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
          
  db:
    image: 'postgres:13.1-alpine'
    container_name: db
    environment:
      - POSTGRES_USER=compose-postgres
      - POSTGRES_PASSWORD=compose-postgres
```





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

#### Versão 1.0.11
- Correção de nomenclaturas de classes e métodos para otimização do código e melhor leitura
- Implementação de uma classe service para organizar os métodos separando a camada de negócio

#### Versão 1.0.12
- Implementação de CI com Travis e deploy automático no Heroku

#### Versão 1.0.13
- Correções da configuração do pipeline no Travis

#### Versão 1.0.14
- Implementação de teste de controllers com Mockito

#### Versão 1.1.0
- Versão final para avaliação