## Sobre
Este repositório contém conta microserviço de Gerenciamento de Processos, desenvolvido com Spring Boot e Postgresql.

## Modo de Uso
1. Clone o repositório do GitHub: [https://github.com/fabiobrasil1/analise_tecnica_po](https://github.com/fabiobrasil1/analise_tecnica_po)
2. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
3. Execute o comando para instanciar o banco de dados:
   ```
   docker-compose up -d
   ```
4. Execute o aplicativo:
   ```bash
   ./mvnw spring-boot:run
   ```

## Postman Collection

* Certifique-se de utilizar a versão mais recente.
Atualizada: 21/12/2023

Caminho do repositório:
`src/main/resources/postman/Analise Técnica P.O - collection.postman_collection.json`

Link abaixo:
- [Analise Técnica P.O - collection](src/main/resources/postman/Analise%20Técnica%20P.O%20-%20collection.postman_collection.json)

* A aplicação conta com a instância automática do banco de dados utilizando Liquibase.

### Fluxo de Autenticação
Você deve fazer login com o usuário administrador cadastrado automaticamente pelo sistema. Exemplo:
```bash
POST http://localhost:8081/auth/login

curl --location 'http://localhost:8081/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email":"admin@admin.com.br",
    "password":"12345"
}'
```

## Endpoints

### User

```
#### Find User by id
- **Método:** GET
- **Endpoint:** http://localhost:8081/users/be330b9c-c792-4dfc-85b9-953ff0c7ac51
- **Autenticação:** Bearer Token
```

#### Register User
- **Método:** POST
- **Endpoint:** http://localhost:8081/users
- **Autenticação:** Bearer Token
- **Body:**
  ```json
  {
    "email": "user@user.com.br",
    "name": "user",
    "password": "12345",
    "role": "USER"
  }
  ```

### Process

#### Register Process
- **Método:** POST
- **Endpoint:** http://localhost:8081/process
- **Autenticação:** Bearer Token
- **Body:**
  ```json
  {
    "userId": "5a425043-500a-403f-ab12-da16d11bd03d",
    "description": "processo description",
    "processNumber": "12345"
  }
  ```

#### Process by Process Number
- **Método:** GET
- **Endpoint:** http://localhost:8081/process/12345
- **Autenticação:** Bearer Token

#### Get Process by User
- **Método:** GET
- **Endpoint:** http://localhost:8081/process/user/5a425043-500a-403f-ab12-da16d11bd03d
- **Autenticação:** Bearer Token

#### Delete Process
- **Método:** DELETE
- **Endpoint:** http://localhost:8081/process/6c2b71fc-472a-48ac-86e6-8865c02c91ec/12345
- **Autenticação:** Bearer Token

#### Add Defendants
- **Método:** POST
- **Endpoint:** http://localhost:8081/process/12345/defendants
- **Autenticação:** Bearer Token
- **Body:**
  ```json
  {
    "defendantName": "Nome do Réu"
  }
  ```

### Auth

#### Login
- **Método:** POST
- **Endpoint:** http://localhost:8081/auth/login
- **Body:**
  ```json
  {
    "email":"admin@admin.com.br",
    "password":"12345"
  }
  ```

**Autor:** Fabio Brasil