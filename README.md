## Sobre
Microserviço de Gerenciamento de Processos
Este repositório contém a implementação de um microserviço utilizando Spring Boot e Postgresql para o gerenciamento de processos judiciais. A seguir, são apresentadas as informações necessárias para entender, executar e contribuir para o projeto.

## Índice
Sobre o Projeto
Estrutura de Pastas
Principais Classes
Controllers
Models
Services
Configurações e Propriedades


## Requisitos Prévios
- Clone o repositório do GitHub: https://github.com/fabiobrasil1/analise_tecnica_po
- Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina.
- Execute o comando para instaciar o banco de dados.
```
docker-compose up -d
```

- Execução do Aplicativo

```
./mvnw spring-boot:run

```

Estrutura do Projeto
# Analise Técnica P.O

## Estrutura de Pastas

```plaintext
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ms/
│   │   │           └── analise_tecnica_po/
│   │   │               ├── AnaliseTecnicaPoApplication.java
│   │   │               ├── auth/
│   │   │               │   ├── controllers/
│   │   │               │   │   └── AuthController.java
│   │   │               │   ├── models/
│   │   │               │   │   └── AuthModel.java
│   │   │               │   └── services/
│   │   │               │       └── AuthService.java
│   │   │               ├── defendant/
│   │   │               │   ├── controllers/
│   │   │               │   │   └── DefendantController.java
│   │   │               │   ├── models/
│   │   │               │   │   └── DefendantModel.java
│   │   │               │   └── repositories/
│   │   │               │       └── DefendantRepository.java
│   │   │               ├── process/
│   │   │               │   ├── controllers/
│   │   │               │   │   └── ProcessController.java
│   │   │               │   ├── models/
│   │   │               │   │   └── ProcessModel.java
│   │   │               │   └── services/
│   │   │               │       ├── AddDefendantUseCase.java
│   │   │               │       ├── DeleteProcessUseCase.java
│   │   │               │       ├── FindProcessByIdUseCase.java
│   │   │               │       ├── GetProcessByUserUseCase.java
│   │   │               │       └── RegisterProcessUseCase.java
│   │   │               ├── user/
│   │   │               │   ├── controllers/
│   │   │               │   │   └── UserController.java
│   │   │               │   ├── models/
│   │   │               │   │   └── UserModel.java
│   │   │               │   ├── repositories/
│   │   │               │   │   └── UserRepository.java
│   │   │               │   └── services/
│   │   │               │       ├── FindUserByIdUseCase.java
│   │   │               │       └── RegisterUserUseCase.java
│   │   │               └── AnaliseTecnicaPoApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── changelog/
│   │               └── master-changelog.xml
├── test/
│   ├── java/
│   │   └── com/
│   │       └── ms/
│   │           └── analise_tecnica_po/
│   │               
│   │               
│   │              
│   │                   
├── .gitignore
├── LICENSE
└── pom.xml
└── docker-compose.yml

## Classes Principais
Controllers
AuthController: Responsável pelo controle de autenticação.
DefendantController: Controla as operações relacionadas aos réus.
ProcessController: Controla as operações relacionadas aos processos.
UserController: Controla as operações relacionadas aos usuários.
Models
DefendantModel: Modelo para representar os réus.
ProcessModel: Modelo para representar os processos.
UserModel: Modelo para representar os usuários.
Services
AddDefendantUseCase: Serviço para adicionar réus a um processo.
DeleteProcessUseCase: Serviço para excluir processos.
FindProcessByIdUseCase: Serviço para encontrar processos por ID.
GetProcessByUserUseCase: Serviço para obter processos por usuário.
RegisterProcessUseCase: Serviço para registrar novos processos.
RegisterUserUseCase: Serviço para registrar novos usuários.
Serviços de Segurança
AutorizationService: Implementa UserDetailsService para carregar detalhes do usuário.
SecurityConfigurations: Configuração de segurança do Spring.
TokenService: Serviço para geração e validação de tokens JWT.
Tratamento de Erros
ErrorHandler: Manipula exceções comuns e fornece respostas apropriadas.
Configurações
SecurityFilterChain: Configuração do filtro de segurança.
Banco de Dados
Liquibase: Configuração para execução de migrações de banco de dados.



Autor: Fabio Brasil
