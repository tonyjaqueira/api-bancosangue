<h1>API-BancoSangue</h1>

![Java](https://img.shields.io/badge/java-version%2017-blue)
![Spring](https://img.shields.io/badge/spring-version%202.7.12-blueviolet)
![Maven Central](https://img.shields.io/badge/maven-version%203.8.1-green)
![lombok](https://img.shields.io/badge/lombok-version%201.18.24-orange)
![Swagger](https://img.shields.io/badge/swagger-version%203.0.0-orange)
![MySql](https://img.shields.io/badge/java-version%208-blue)

<h3>Descrição da API</h3>
A api tem como objetivo servir para teste de avaliação da empresa para contratação de profissional de desenvolvimento.

<h3>Status</h3>
![Badge Concluída](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDA&color=GREEN&style=flat-square&logo=appveyor)

<h3>Acesso</h3>
https://github.com/tonyjaqueira/api-bancosangue

<h3>Executar</h3>

AS variáveis de ambiente que precisam serem criadas são as seguintes:
* DATA_BASE -> para Banco de Dados
* DATA_BASE_USERNAME -> para usuário do Banco de Dados
* DATA_BASE_PASSWORD -> para senha do Banco de Dados

Para executar o projeto é necessário obter as valores de variaveis de ambiente que constam em application.properties

```shell
mvn spring-boot:run
```

<h3>Swagger da API</h3>
http://localhost:8085/swagger-ui/index.html

<h3>Tecnologias utilizadas</h3>

* Swagger
* Mysql
* Spring Security
* Token JWT
* Lombok
* Spring Data

<h3>Desenvolvedor</h3>
Antônio da Silva Araújo Neto
