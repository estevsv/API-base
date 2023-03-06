# 🚨 Overview

- A API construída em Java utilizando Spring Framework
- Implementação de autenticação seguindo o padrão ***JWT***, lembrando que o token a ser recebido deve estar no formado ***Bearer***
- Implementação de operações no banco de dados utilizando ***Spring Data JPA*** & ***Hibernate***
- **Bancos relacionais**
    - *MySQL*
- As entidades criadas como tabelas utilizando a ferramenta de migração **Flyway**.
- Padrões REST na construção das rotas e retornos
- Documentação viva utilizando a *OpenAPI Specification* (**Swagger**)
- Mais informações técnicas no arquivo "Informações Adicionais"

# 🕵🏻‍♂️ Áreas de Estudo na API

- Estrutura do projeto
- Utilização de código limpo e princípios **SOLID**
- Segurança da API, como autenticação, senhas salvas no banco, *SQL Injection* e outros
- Boas práticas da Linguagem/Framework

# 🖥 Contexto da API

Criada uma API exemplo que o site [IMDb](https://www.imdb.com/) poderia usar para exibir e cadastrar seu conteúdo, contendo as seguintes funcionalidades:

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