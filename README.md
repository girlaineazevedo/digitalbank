# Digital Bank

Repositório contendo API para suportar o funcionamento de um banco digital.

1. [Especificação](https://trello.com/b/aiSaSAbi/nosso-banco-digital)

## Pré-requisitos
- Java 8
- Docker

Obs: Pode haver conflito na porta usada pelo MySQL na execução dos containers com o serviço do Windows (Porta 3306).

## Executando os containers

> - **Baixar o projeto**
```cmd
git clone https://github.com/girlaineazevedo/digitalbank.git
```
> - **Rodar a aplicação**
```cmd
mvn spring-boot:run
```
> - **Executar os containers**
```cmd
docker-compose up
```

## Acessando a aplicação
A aplicação estará disponível através do caminho
>http://localhost:8180

O endpoint **/proposal** retorna no body a chave da proposta que será usada nas outras requisições.

### Funcionalidades

A API DigitalBank possui as seguintes funcionalidades:
- Criar uma proposta para criação de nova conta de pessoa física(em etapas)
- Login/Autenticação
- Transferencia para contas de outros bancos

### Configuração
> - **O envio de e-mail é feito apenas no ambiente prod.**
Para testar é necessário alterar o profile no arquivo application.properties para spring.profiles.active=prod.
Também é necessário preencher as seguintes configurações no application-prod.properties com um e-mail válido.
-- spring.mail.username=
-- spring.mail.password=
-- email.from=
SMTP do outlook configurado, caso use outro servidor de e-mail, trocar o SMTP.
### Endpoints

Todos os endpoints estão disponíveis através do Swagger, ao executar a aplicação acesse a documentação através do link:

>http://localhost:8180/swagger-ui.html

O arquivo chamado **ApiBank-postman.json** na raíz do projeto está pré-configurado para testes e pode ser importado no Postman.

### Tecnologias Utilizadas

- Java 8

- Spring Boot (Spring Data (JPA), Spring Security, Spring Web)

- Banco de dados H2 para testes ambiente dev

- Docker - Imagem do MySQL para testes ambiente prod

- Jwt

- Swagger

- Postman

- Eclipse IDE