# Biblioteca JDBC 📚💻

Este projeto é uma extensão da aplicação de biblioteca apresentada em aula, onde o repositório que armazenava a lista de livros em memória foi adaptado para utilizar JDBC e acessar um banco de dados relacional. Além disso, foi implementado um repositório para gerenciar os usuários da biblioteca, possibilitando a retirada e devolução de livros de forma controlada.

## Funcionalidades

- **Listagem de livros**: Mostra todos os livros cadastrados na biblioteca.
- **Registro de livros no banco de dados**: Utilizando JDBC, os livros são armazenados e acessados diretamente no banco de dados.
- **Controle de empréstimos**: Permite registrar a retirada de um livro por um usuário e devolvê-lo quando necessário.
- **Listagem de livros disponíveis**: Exibe os livros que estão disponíveis (não emprestados).
- **Listagem de livros emprestados a um usuário**: Exibe os livros atualmente emprestados por um determinado usuário.

## Como executar 

### Pré-requisitos

- **Java 21**: Certifique-se de que o [JDK 21](https://www.java.com/pt-BR/download/) esteja instalado.
- **Maven**: É necessário ter o [Maven](https://maven.apache.org/index.html) configurado para gerenciar as dependências e o ciclo de vida do projeto.
  
1. Utilize uma IDE (ferramenta de desenvolvimento), que seja Java executável. 
2. Baixe ou clone este repositório.
3. Na pasta raíz do projeto `Ex1BiblioecaApplication`, utilize o comando `mvn spring-boot:run`.
4. Em sua máquina local, abra o navegador e acesse a URL: http://localhost:8080

## Endpoints disponíveis
- http://localhost:8080/livros – Retorna a lista de todos os livros cadastrados.
- http://localhost:8080/autores – Retorna a lista de todos os autores cadastrados.
- http://localhost:8080/livrosAutor/{autor} – Retorna a lista de todos os livros cadastrados de um determinado autor.
- http://localhost:8080/livrosAutor/{autor}/ano/{ano} - Retorna a lista de todos os livros cadastrados de um determinado autor e ano.
- http://localhost:8080/livrosDisponiveis – Lista todos os livros que não estão emprestados.
- http://localhost:8080/livrosEmprestados/{codigoUsuario} – Lista todos os livros que estão emprestados para um determinado usuário.
- http://localhost:8080/devolveLivro/{titulo} - Realiza a devolução de um livro.
- http://localhost:8080/emprestaLivro/{titulo}/{codigoUsuario} – Realiza a retirada de um livro pelo usuário.

## Autores
[Eduarda dos Santos Patricio](https://github.com/e-patricio)

[Maria Eduarda Santellano Maidana Carvalho](https://github.com/MariaMaidana)

[Yasmin Cardozo Aguirre](https://github.com/4gu1rr3)
