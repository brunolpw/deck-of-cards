# Teste klab

- [Descrição](#descrição)
- [Documentação](#documentação)
- [Links importantes](#links-importantes)

## Descrição
O desafio consiste em criar um baralho (utilizando a API disponível no fim do arquivo) e montar quatro “mãos” com 5 cartas cada uma, verificando qual “mão” tem a maior somatória.

Se houver empate, retornar só os vencedores empatados.

Regras:
- A = 1
- K = 13
- Q = 12
- J = 11

Exemplo:
```
Jogador 1 = [A,2,3,4,5]
Jogador 2 = [K,Q,J,10,9]
Jogador 3 = [8,9,2,A,J]
Jogador 4 = [2,2,5,7,2]
Vencedor é Jogador 2 com 55 pontos
```

Requisitos:
- Utilizar Spring Boot/ Java 8+;
- Utilizar o padrão "REST API";
- Será permitido o uso do Feign;
- O projeto deverá estar disponível no Github:
- Criar commits descritivos;

- Testes unitários; (OBRIGATÓRIO)
- Salvar em banco de dados; (OBRIGATÓRIO)
- Links: https://deckofcardsapi.com/ (API deck de cartas)

--- 

## Documentação

Para o desenvolvimento deste projeto foram utilizadas as seguintes tecnologias:

- Java 21
- Spring Boot
- Feign Client
- Postgres
- Swagger
- Docker
- Git
- GitHub
- Github Actions para CI
- Github Container Repository (ghcr.io)

O projeto constitui uma API REST contendo 4 controladores com seus respectivos endpoints. Os testes foram feitos através do GitHub Actions para automatizar a cada deploy e garantir a consistência.

Os controladores e seus respectivos endpoints são:

- **card-controller:**
- - `/cards/getAll`: Não recebe nenhum parâmetro e retorna uma lista no seguinte formato:
    ```json
    [
        {
            "code": "string",
            "value": 0,
            "suit": "string"
        }
    ]
    ```  
- **hand-controller:**
- - `GET` `/hands/getAll`: Não recebe nenhum parâmetro e retorna uma lista no seguinte formato:
    ```json
    [
        {
            "handId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "success": true,
            "deckId": "string",
            "cardCodes": [
            "string"
            ],
            "game": {
            "gameId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "deckId": "string"
            }
        }
    ]
    ```
- - `GET` `/hands/getAllByGameId`: Recee `gameId` como parâmetro e retorna uma lista no seguinte formato: 
    ```json
    [
        {
            "handId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "success": true,
            "deckId": "string",
            "cardCodes": [
            "string"
            ],
            "game": {
            "gameId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "deckId": "string"
            }
        }
    ]
    ```
- **deck-controller:**
- - `PUT` `/decks/shuffle`: Este endpoint faz chamada a uma API externa e recebe como parâmetro o ID de um card e embaralha os valores de cartas, retorna no seguinte formato:
    ```json
    {
        "deckId": "string",
        "remaining": 0,
        "success": true,
        "shuffled": true
    }
    ```
- - `GET` `/decks/new`: Este endpoint faz chamada a uma API externa e não recebe nenhum parâmetro e retorna no seguinte formato, as cartas não são embaralhadas, retornando de forma ordenada:
    ```json
    {
        "deckId": "string",
        "remaining": 0,
        "success": true,
        "shuffled": true
    }
    ```
- - `GET` `/decks/newShuffled`: Este endpoint faz chamada a uma API externa e não recebe nenhum parâmetro e retorna no seguinte formato, diferente do anterior, aqui as cartas já são embaralhadas, retornando de forma ordenada:
    ```json
    {
        "deckId": "string",
        "remaining": 0,
        "success": true,
        "shuffled": true
    }
    ```
- - `GET` `/decks/getAll`: Não recebe nenhum parâmetro e retorna uma lista no seguinte formato:
    ```json
    [
        {
            "deckId": "string",
            "remaining": 0,
            "success": true,
            "shuffled": true
        }
    ]
    ```
- **game-controller:**
- - `POST` `/games/new`: Este é o principal endpoint do sistema, ele é responsável por todo o jogo e receberá um objeto como parâmetro, o objeto será no seguinte formato:
    ```json
    {
        "deckId": "string",
        "countHands": 0,
        "countCards": 0
    }
    ```
    Caso desejar, poderá passar o parâmetro `deckId` como uma string em branco, como neste segundo caso, com isso o jogo irá gerar um novo baralho já embaralhado e efetuar o jogo.
    ```json
    {
        "deckId": "",
        "countHands": 0,
        "countCards": 0
    }
    ```
    O retorno será um objeto contendo todas as mãos jogadas, o valor de cada uma delas e uma lista contendo o(s) vencedor(es).
    ```json
    {
        "gameId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "deckId": "string",
        "hands": [
            {
            "handId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "cards": [
                "string"
            ],
            "value": 0
            }
        ],
        "winner": [
            {
            "handId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "cards": [
                "string"
            ],
            "value": 0
            }
        ]
    }
    ```
- - `GET` `/games/getAll`: Não recebe nenhum parâmetro e retorna uma lista no seguinte formato:
    ```json
    [
        {
            "gameId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "deckId": "string"
        }
    ]
    ```

Acessando localmente poderá acessar através da porta `8080` poderá acessar o [Swagger](http://localhost:8080/swagger-ui/index.html) para melhor visualização.

O projeto encontra-se no [GitHub](https://github.com/brunolpw/deck-of-cards).

Fazendo o clone do projeto você encontrará um arquivo docker [compose.yml](./compose.yml), basta rodar o comando `docker compose up -d` para subir a aplicação e o banco de dados. Este irá baixar a imagem do meu  repositório que encontra-se nos [packages](https://github.com/brunolpw/deck-of-cards/pkgs/container/deck-of-cards-app) do projeto. Caso nunca tenha utilizado o repositório `ghrc.io` é provável que seja solicitado para fazer login na primeira vez, deixo nos [links importantes](#links-importantes) um material de de como proceder.

---
 ## Links importantes

 [Como se autenticar com um personal access token (classic)](https://docs.github.com/pt/packages/working-with-a-github-packages-registry/working-with-the-container-registry#como-se-autenticar-com-um-personal-access-token-classic)