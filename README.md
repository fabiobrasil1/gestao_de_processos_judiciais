markdown
Copy code
## Sobre
Este repositório contém a coleção do Postman para o microserviço de Gerenciamento de Processos, desenvolvido com Spring Boot e Postgresql.

## Endpoints

### User

#### Find User by id
- **Método:** GET
- **Endpoint:** http://localhost:8081/users/ce8345ac-80c8-49c0-82d8-1e83ebe5588b
- **Autenticação:** Bearer Token

#### Register User
- **Método:** POST
- **Endpoint:** http://localhost:8081/users
- **Autenticação:** Bearer Token
- **Body:**
  ```json
  {
    "email": "admin@admin2.com.br",
    "name": "admin name",
    "password": "123456",
    "role": "ADMIN"
  }
Process
Register Process
Método: POST
Endpoint: http://localhost:8081/process
Autenticação: Bearer Token
Body:
json
Copy code
{
  "userId": "2fdc0d41-c186-429a-8d3c-f1fc10dcf580",
  "description": "teste2",
  "processNumber": "12345"
}
Process by id
Método: GET
Endpoint: http://localhost:8081/process/203f9901-d1e4-4738-93c8-a313a5e280d5
Autenticação: Bearer Token
Get Process by User
Método: GET
Autenticação: Bearer Token
Delete Process
Método: GET
Autenticação: Bearer Token
Add Defendants
Método: POST
Endpoint: http://localhost:8081/process/af888be8-393e-478c-90d8-c7664e2019fb/defendants
Autenticação: Bearer Token
Body:
json
Copy code
{
  "defendantName": "Nome do Réu"
}
Auth
Login
Método: POST
Endpoint: http://localhost:8081/auth/login
Body:
json
Copy code
{
  "email": "admin@amin.com.br",
  "password": "12345"
}
Modo de Uso
Clone o repositório do GitHub: https://github.com/fabiobrasil1/analise_tecnica_po
Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
Execute o comando para instanciar o banco de dados:
bash
Copy code
docker-compose up -d
Execute o aplicativo:
bash
Copy code
./mvnw spring-boot:run
Postman Collection
Analise Técnica P.O - collection
A aplicação conta com a instância automática do banco de dados utilizando Liquibase.

Fluxo de Autenticação
Você deve fazer login com o usuário administrador cadastrado automaticamente pelo sistema. Exemplo:

bash
Copy code
POST http://localhost:8081/auth/login

curl --location 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"admin@admin.com.br",
    "password":"12345"
}'
Siga os passos adicionais para chamar os endpoints conforme necessário.

Autor: Fabio Brasil