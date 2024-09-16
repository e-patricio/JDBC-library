package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJDBCImpl implements IUsuarioRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Usuario> getAll() {
    List<Usuario> resp = this.jdbcTemplate.query("SELECT * from usuarios",
        (rs, rowNum) -> new Usuario(
                rs.getInt("codigo"),
                rs.getString("nome"),
                rs.getInt("anoNasc")));
    return resp;
    }

    @Override
    public Usuario getUsuario(long codigo) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM usuarios WHERE codigo = ?", 
            (rs, rowNum) -> new Usuario(
                rs.getLong("codigo"),
                rs.getString("nome"),
                rs.getInt("anoNasc")),
            codigo);
        }

    @Override
    public boolean removeUsuario(long codigo) {
        this.jdbcTemplate.update("DELETE FROM usuarios WHERE codigo = ?", codigo);
        return true;
    }

    @Override
    public boolean cadastraUsuarioNovo(Usuario usuario) {
        this.jdbcTemplate.update("INSERT INTO usuarios(codigo, nome, anoNasc) VALUES (?,?,?)",
        usuario.getCodigo(), usuario.getNome(), usuario.getAnoNasc());
        return true;
    }   
}