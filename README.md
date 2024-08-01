![Universidade](https://badgen.net/badge/Univesidade/Universidade%20Federal%20do%20Ceará/blue)
![Cadeira](https://badgen.net/badge/Cadeira/Programação%20Web/red)
![Professor](https://badgen.net/badge/Professor/Leonardo/red)
![Semestre](https://badgen.net/badge/Semestre/6/red)

![MIT](https://img.shields.io/github/license/PWEB-ECOMMERCE/Front.svg)
![issues](https://img.shields.io/github/issues/PWEB-ECOMMERCE/Front.svg)
# PWEB - Back
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

Este é um projeto para a disciplina de Programação Web do curso de Sistemas e Mídias Digitais da Universidade Federal do
Ceará - UFC.

Utilizaremos o Spring framework com Java para desenvolver o serviço web do projeto, na forma de API RESTful. Nossa solução
para persistência de dados será o PostgreSQL, que se comunicará com a aplicação por meio de bibliotecas como Hibernate e Flyway!

Nosso projeto é um e-commerce que será gerenciado pelo proprietário da loja e os clientes comprarão itens exibidos na
loja.<br>
## Como Rodar

As dependências são geridas pelo Maven, então tenha isso em mente. Utilize, de preferência, uma IDE com suporte ao Maven para simplificar as coisas.

## Infra

Por enquanto, temos um arquivo docker-compose que será usado para gerar toda a aplicação para nós: frontend, backend e
banco de dados. Contudo, esse aquivo, juntamente com as intruções para executá-lo, estão no [repositório do Frontend](https://github.com/PWEB-ECOMMERCE/Front/tree/develop).
As instruções aqui serão exclusivamente para executar o backend localmente, caso deseje rodar a aplicação completa ou apenas o frontend, você pode se referir à [esse link](https://github.com/PWEB-ECOMMERCE/Front/blob/develop/README.md).

### Desenvolvedores

### Método 1 (Aplicação Spring + Container Postgres Alpine)


Dependencias
- [Docker](https://www.docker.com/)
- [postgres:alpine Docker image](https://hub.docker.com/_/postgres)


Para rodar o projeto localmente são necessários alguns passos:<br>
  1. Clonar a branch `main` (Builds mais estáveis)
  2. Crie e execute um container Docker contendo uma instância do PostgreSQL (Versões mais recentes. Tag ':alpine' é recomendada). 
  3. Com as credenciais do container e a porta exposta, configure o arquivo `application.properties` em `src/main/resources/`.
  4. Execute a classe principal para inicializar o servidor!


Com esse método, para fins de transparência e simplicidade, ferramentas como o [DBeaver](https://dbeaver.io/download/) podem ser muito úteis uma vez
que permitem a visualização do banco de dados mesmo que estejam em um container Docker!
  

### Método 2 (Aplicação Spring + Database na instalação local do Posgres)


Dependencias
- [PostgreSQL](https://www.postgresql.org/download/)


Esse método é mais direto e visual, porém bem menos replicável. Contudo, pode ser bom em casos de não poder usar o Docker.<br>
Alguns passos são bastantes similares ao método 1 acima:
  1. Clonar a branch `main`
  2. Instale o PostgreSQL localmente na sua maquina (versão 14+) e configure as credenciais. 
  3. Com as credenciais banco (porta padrão é 5432), configure o que for necessário no arquivo `application.properties` em `src/main/resources/`.
  4. Execute a classe principal para inicializar o servidor!
