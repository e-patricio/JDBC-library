package com.gurias.ex1biblioeca;

import java.util.List;

public interface IAcervoRepository {
    List<Livro> getAll();
    List<String> getTitulos();
    List<String> getAutores();
    List<Livro> getLivrosDoAutor(String autor);
    Livro getLivroTitulo(String titulo);
    boolean cadastraLivroNovo(Livro livro);
    boolean removeLivro(long codigo);
    boolean emprestaLivro(Long codigo, long codigoUsuario);
    boolean devolveLivro(long codigo);
}
