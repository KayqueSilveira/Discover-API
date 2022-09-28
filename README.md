# Discover-API

API de cadastro de clientes com vinculação de um cartão de crédito.

Tecnologias:

* SpringBoot
* JPA
* Lombok
* Maven
* Banco H2

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main ApiApplication.

Ao subir pode-se acessar:

```
Para cadastrar um novo cliente:
localhost:8080/api/v1/cliente/criar

Para verificar todos os clientes:
localhost:8080/api/v1/cliente/buscar

Para verificar cliente expecifico digitando seu id:
localhost:8080/api/v1/cliente/buscar/<id do cliente>

Para alterar as propriedados do cliente expecifico digitando o id do Cliente e o id do Cartao:
localhost:8080/api/v1/cliente/update?idCliente=<valor>&idCartao=<valor>

Para cadastrar um novo cartao deve colocar como parametro o id do Cliente para vincular:
localhost:8080/api/v1/cartao/criar?idCliente=<valor>

Para verificar a lista de cartoes e suas transacoes com cliente:
localhost:8080/api/v1/cartao/buscar

Para verificar um cartao expecifico e suas transacoes com o cliente:
localhost:8080/api/v1/cartao/buscar/<id do cartao>

Para deletar um cliente é necessário também excluir o cartao vinculado e as transações:
localhost:8080/api/v1/cartao/deletar?idCartao=<valor>
>Obs: Foi colocado idCartao devido o cartao ja possuir os dados do cliente e da transação
```

## Banco de Dados

Foi utilizado o banco H2. Para acessar o console execute a aplicação e acesse pelo navegador localhost:8080/h2-console:

```
Usuario: sa
Senha: 
Banco: jdbc:h2:mem:apiDiscover
```
