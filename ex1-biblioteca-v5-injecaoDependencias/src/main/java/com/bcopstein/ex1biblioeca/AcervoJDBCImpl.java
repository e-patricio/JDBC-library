package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AcervoJDBCImpl implements IAcervoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcervoJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Livro> getAll() {
    List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros",
        (rs, rowNum) -> new Livro(
                rs.getInt("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano"),
                rs.getInt("codUsuario")));
    return resp;
    }

    @Override
    public boolean removeLivro(long codigo) {
        this.jdbcTemplate.update("DELETE FROM livros WHERE codigo = ?", codigo);
        return true;
    }

    public boolean cadastraLivroNovo(Livro livro) {
        this.jdbcTemplate.update("INSERT INTO livros(codigo, titulo, autor, ano) VALUES (?,?,?,?)",
        livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno());
        return true;
    }

    @Override
    public List<String> getAutores() {
    return this.jdbcTemplate.query("SELECT DISTINCT autor FROM livros", 
        (rs, rowNum) -> rs.getString("autor"));
    }
    
    @Override
    public Livro getLivroTitulo(String titulo) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM livros WHERE titulo = ?", 
            (rs, rowNum) -> new Livro(
                rs.getLong("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano"),
                rs.getInt("codUsuario")),
            titulo);
        }

        @Override
        public List<Livro> getLivrosDoAutor(String autor) {
            return this.jdbcTemplate.query("SELECT * FROM livros WHERE autor = ?", 
            (rs, rowNum) -> new Livro(
                rs.getLong("codigo"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano"),
                rs.getInt("codUsuario")),
            autor);
        }

        @Override
        public List<String> getTitulos() {
            return this.jdbcTemplate.query("SELECT titulo FROM livros", 
                (rs, rowNum) -> rs.getString("titulo"));
        }
        
        @Override
        public boolean devolveLivro(long codigo) {
            int rowsAffected = this.jdbcTemplate.update("UPDATE livros SET codUsuario = -1 WHERE codigo = ?",
                codigo);
            return rowsAffected > 0;
        }

        @Override
        public boolean emprestaLivro(Long codigo, long codigoUsuario) {
            int rowsAffected = this.jdbcTemplate.update("UPDATE livros SET codUsuario = ? WHERE codigo = ?",
                codigoUsuario, codigo);
            return rowsAffected > 0;
        }
        
}

