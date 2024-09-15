DROP TABLE IF EXISTS livros;
CREATE TABLE livros (codigo bigint,titulo VARCHAR(255),autor VARCHAR(255),ano int, codUsuario int DEFAULT - 1, PRIMARY KEY(codigo));
