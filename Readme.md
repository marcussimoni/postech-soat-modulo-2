## PosTech Software Architecture

Projeto do tech challenge para avaliação do módulo 2 do curso Software Architecture.

### Api backend

Api backend contendo os endpoints que representam todo o processo. A api foi criada utilizando a linguagem Java junto com o Framework Spring boot, Domain Driven Design e Arquitetura Hexagonal. Os seguintes pré requisitos são necessários para construir e executar a aplicação:

- Java 17
- Apache maven 3.8.2
- Docker 

A primeira etapa consiste em gerar o build da api backend. Para esta etapa é necessário, a partir do diretório raiz do projeto, executar o seguinte comando maven no terminal:
`mvn clean package`

### Dockerfile e Docker Compose para execução da api com o banco de dados.

Com o build da api backend concluído, as etapas para construção da imagem e execução do docker compose podem ser realizadas com os seguintes comandos a partir da raiz do projeto: 

- Comando do docker para construir a imagem da aplicação
  `docker build -t postech-soat-modulo-1 .`
- Comando do docker compose responsável por iniciar as imagens da api backend junto da imagem do postgresql, que será utilizado como banco de dados. `docker-compose -f ./docker/docker-compose.yml up`

## Acesso a documentação da api (Swagger)

Com a api e o banco de dados iniciados, podemos acessar a documentação da api a partir do endereço ***[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)***

### Etapas para realizar pedido (Totem de auto atendimento)

O processo para realizar um pedido através dos endpoints da api deve seguir os seguintes passos:

1. Verificar se o cliente possui cadastro ou cadastra-lo caso não seja cadastrado no sistema ainda:
   1. Para verificar se o cliente possui cadastro podemos utilizar o endpoint: ***[atendimento/cliente/buscar](http://localhost:8080/swagger-ui/index.html#/Totem%20de%20auto%20atendimento/buscarClientePorCpf)***
   2. Cadastrar o novo cliente no endpoint: ***[atendimento/cliente/cadastrar](http://localhost:8080/swagger-ui/index.html#/Totem%20de%20auto%20atendimento/cadastrarNovoCliente)***
2. A segunda etapa consiste em apresentar os produtos disponíveis para compor o pedido. Os pedidos podem ser acessados em: ***[atendimento/produtos](http://localhost:8080/swagger-ui/index.html#/Totem%20de%20auto%20atendimento/buscarProdutosPorCategoria)***
3. Após os produtos escolhidos o checkout do pedido pode ser concluido pelo endpoint ***[atendimento/pedido/checkout](http://localhost:8080/swagger-ui/index.html#/Totem%20de%20auto%20atendimento/checkout)***. O cliente é opcional durante o checkout, se o cliente desejar se identificar é necessário incluir o cpf do cliente no payload do pedido.
4. Com o checkout realizado o pedido só fica disponível para preparo após o pagamento do pedido. O pagamento pode ser realizado pelo endpoint: ***[atendimento/pedido/pagamento](http://localhost:8080/swagger-ui/index.html#/Totem%20de%20auto%20atendimento/pagamento)***

### Atualizar as etapas do pedido e acompanhamento das etapas pelo cliente e atendimento

Após o pedido ser realizado e pago pelo cliente a cozinha pode iniciar o preparo.

1. O acompanhamento do status do pedido pode ser feito por dois endpoins:
   1. Pelo número do pedido ***[pedido/buscar-por-numero](http://localhost:8080/swagger-ui/index.html#/Pedidos/buscarPedidoPorId)***. Pode ser utilizado pelo atendente para para verificar pedidos em qualquer etapa e que foram ou não pagos.
   2. Todos os pedidos pagos e aguardando a conclusão do preparo ***[pedido](http://localhost:8080/swagger-ui/index.html#/Pedidos/listar_1)***. Nesse endpoint só serão listados os pedidos com status de pagamento igual a Pago  para que o cliente possa acompanhar em qual etapa o seu pedido se encontra.
2. A equipe responsável pelo preparo dos pedidos pode atualizar as etapas a partir do endpoint: ***[pedido/proxima-etapa/{pedido}](http://localhost:8080/swagger-ui/index.html#/Pedidos/atualizaParaEmPreparacao)***

### Áreas administrativas

Para cadastrar novos produtos ou clientes podemos utilizar os seguintes endpoints:

1. Para produtos: ***[produtos](http://localhost:8080/swagger-ui/index.html#/%C3%81rea%20administrativa%20-%20Produtos)***
2. Para clientes: ***[clientes](http://localhost:8080/swagger-ui/index.html#/%C3%81rea%20administrativa%20-%20Clientes)***
