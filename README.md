# Desafio Pessoa Desenvolvedora Java

# 🚨 Requisitos

- A API deve ser construída em Java utilizando Spring Framework
- Implementar autenticação seguindo o padrão ***JWT***, lembrando que o token a ser recebido deve estar no formado ***Bearer***
- Implementar operações no banco de dados utilizando ***Spring Data JPA*** & ***Hibernate***
- **Bancos relacionais permitidos**
    - *MySQL* (prioritariamente)
    - *PostgreSQL*
- As entidades deversão ser criadas como tabelas utilizando a ferramenta de migração **Flyway**.
- Sua API deverá seguir os padrões REST na construção das rotas e retornos
- Sua API deverá conter documentação viva utilizando a *OpenAPI Specification* (**Swagger**)
- Caso haja alguma particularidade de implementação, instruções para execução do projeto deverão ser enviadas

# 🎁 Extra

- Testes unitários
- Teste de integração da API em linguagem de sua preferência (damos importância para pirâmide de testes)
- Cobertura de testes utilizando Sonarqube
- Utilização de *Docker*

# 🕵🏻‍♂️ Validar

- Estrutura do projeto
- Utilização de código limpo e princípios **SOLID**
- Segurança da API, como autenticação, senhas salvas no banco, *SQL Injection* e outros
- Boas práticas da Linguagem/Framework
- Seu projeto deverá seguir tudo o que foi exigido na seção  [O que desenvolver?](##--o-que-desenvolver)

# 🖥 O que desenvolver?

Você deverá criar uma API que o site [IMDb](https://www.imdb.com/) irá consultar para exibir seu conteúdo, sua API deverá conter as seguintes funcionalidades:

- Administrador
    - Cadastro
    - Edição
    - Exclusão lógica (desativação)
    - Listagem de usuários não administradores ativos
        - Opção de trazer registros paginados
        - Retornar usuários por ordem alfabética
- Usuário
    - Cadastro
    - Edição
    - Exclusão lógica (desativação)
- Filmes
    - Cadastro (somente um usuário administrador poderá realizar esse cadastro)
    - Voto (a contagem de votos será feita por usuário de 0-4 que indica quanto o usuário gostou do filme)
    - Listagem
        - Opção de filtros por diretor, nome, gênero e/ou atores
        - Opção de trazer registros paginados
        - Retornar a lista ordenada por filmes mais votados e por ordem alfabética
    - Detalhes do filme trazendo todas as informações sobre o filme, inclusive a média dos votos

**Obs.:** 

**Apenas os usuários poderão votar nos filmes e a API deverá validar quem é o usuário que está acessando, ou seja, se é um usuário administrador ou não.**