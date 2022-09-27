# Discover-API

API de cadastro de clientes com vinculação de um cartão de crédito.

Tecnologias:

* SpringBoot
* JPA e SpringData
* Maven
* Banco H2

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main ApiApplication.

Ao subir pode-se acessar:

```
Para verificar todos os clientes:
http://localhost:8080/api/v1/buscar/cliente
Para verificar cliente expecifico digitando seu id:
http://localhost:8080/api/v1/buscar/cliente/<id do cliente>
Para verificar a lista de cartoes e suas transacoes com cliente:
http//localhost:8080/api/v1/buscar/cartao
Para verificar um cartao expecifico e suas transacoes com o cliente:
http//localhost:8080/api/v1/buscar/cartao/<id do cartao>
```

## Banco de Dados

Foi utilizado o banco H2. Para acessar o console execute a aplicação e acesse pelo navegador localhost:8080/h2-console:

```
Usuario: sa
Senha: 
Banco: jdbc:h2:mem:apiDiscover
```
