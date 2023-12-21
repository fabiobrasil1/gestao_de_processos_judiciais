## Sobre
Microserviço de Gerenciamento de Processos.
Este repositório contém a implementação de um microserviço utilizando Spring Boot e Postgresql para o gerenciamento de processos judiciais. A seguir, são apresentadas as informações necessárias para entender, executar e contribuir para o projeto.


## Requisitos da aplicação.

Utilizando Spring Boot e Postgresql como banco de dados crie um microsserviço com as funcionalidades descritas abaixo:
1. Como usuário preciso salvar meus números de processos no sistema, quero poder enviar estes números através de uma requisição POST.
- Obs : Os números de processos devem ser únicos
2. Como usuário quero receber um erro ao tentar cadastrar um processo que já foi cadastrado anteriormente.
3. Como usuário quero poder consultar os números de processos que salvei. 4. Como usuário quero poder excluir um número de processo que salvei.
5. Como usuário quero poder adicionar um Réu a um processo que já cadastrei anteriormente.


## Modo de Usar
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


## Exemplos de requisição:
* Postman collection - atualizada: 21/12/2023

* Certifique-se de ultilizar a versão mais recente encontrado diretorio a baixo:
  
src/main/resources/postman/Analise Técnica P.O - collection.postman_collection.json


Autor: Fabio Brasil
