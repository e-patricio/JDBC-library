package com.gurias.ex1biblioeca;

import java.util.List;

public interface IUsuarioRepository {
    List<Usuario> getAll();
    Usuario getUsuario(long codigo);
    boolean cadastraUsuarioNovo(Usuario usuario);
    boolean removeUsuario(long codigo);
}