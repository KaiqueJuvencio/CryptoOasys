#  🪙 CryptoOasys 🪙

CryptoOasys é uma plataforma para favoritar, monitorar informações sobre suas cripto‑moedas favoritas e fazer notas sobre cada uma.

## Features

- Cadastro de usuário :heavy_check_mark:
- Login de usuário :heavy_check_mark:
- Listar moedas (incluir se a moeda é um favorito) :heavy_check_mark:
- Listar favoritos (trazer informações das moedas) :heavy_check_mark:
- Favoritar moeda :heavy_check_mark:
- Modificar anotação :heavy_check_mark:
- Remover favorito :heavy_check_mark:

## API Consumida (Paprika API)
- https://api.coinpaprika.com/

## Tech Stack

<div style="display: inline_block">
    <img align="center" alt="Java" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
    <img align="center" alt="Postgres" height="50" width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" />
    <img align="center" alt="Docker" height="70" width="70" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" />
    <img align="center" alt="Redis" height="50" width="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" />
</div>
<br>
Tecnologias usadas para construir a aplicação

- Java (jdk-11.0.2) | Spring Boot, JPA, Hibernate, JUnit, Swagger, OpenFeign (Spring Cloud), Devtools, Maven (3.8.5), Lombok, Validators
- Postgres
- Redis (Cache/ Login)
- Docker
- Docker Compose

### :hot_pepper: Lombok:
Como instalar o Lombok para sua IDE disponível em: https://projectlombok.org/setup/overview

## Local Setup

Para iniciar a aplicação local, execute os seguintes comandos na raiz do projeto:


1º - Buildar imagens Docker
```bash
docker-compose build --no-cache
```
2º - Subir o postgres
```bash
docker-compose up db
```
3º - Clean install no backend
```bash
mvn clean install
```
4º - Subir o projeto
```bash
docker-compose up
```

Vai rodar três containers: 

- Container Postgres database (postgres-db) |  localhost:15432
- Container Redis database (redis-db) |  redis:6379
- Container Java backend  (java-app) | localhost:8080

## Documentação
- Swagger disponível em: http://localhost:8080/swagger-ui.html
- Arquivo Postman disponível na raiz do projeto: cryptoOasys.postman_collection.json 
- No arquivo do Postman é possível bater na API consumida pelo backend da PAPRIKA API

## 📊 Postgres Database Tables


### TB_FAVORITE_COIN

| Fields                  |  Type        | Description |
| ----------------------- | :----------: | ----------: |
| id                      | varchar(255) | Id da moeda                   |
| name                    | varchar(255) | Nome da moeda                 |
| notes                   | varchar(255) | Nota feita pelo usuário       |
| symbol                  | varchar(255) | Símbolo da moeda              |
| user_id                 | varchar(255) | Usuário que favoritou         |
| created                 | timestamp    | Data de criação               |
| updated                 | timestamp    | Data que a nota foi editada   |

### TB_USER

| Fields                  |  Type        | Description |
| ----------------------- | :----------: | ----------: |
| id                      | int          | Id do usuário          |
| name                    | varchar(255) | Nome do usuário        |
| nick_name               | varchar(255) | Apelido do usuário     |
| password                | varchar(255) | Senha do usuário       |

## 📊 Redis Database Keys

| Fields                  |  Type        | Description |
| ----------------------- | :----------: | ----------: |
| userLoggedName          | String       | Nome do usuário logado |
| userLoggedNick          | String       | Apelido do usuário logado |


