package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private IAcervoRepository livros;
    private IUsuarioRepository usuarios;

    @Autowired
    public Controller(IAcervoRepository livros , IUsuarioRepository usuarios) {
        this.livros = livros; 
        this.usuarios = usuarios;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return livros.getAll();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return livros.getAutores();
    }

    @GetMapping("livrosAutor/{autor}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value = "autor") String autor) {
        return livros.getLivrosDoAutor(autor);
    }

    @GetMapping("livrosAutor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value="autor") String autor, @PathVariable(value="ano")int ano) {
        return livros.getLivrosDoAutor(autor)
                .stream()
                .filter(l -> l.getAno() == ano)
                .toList();
    }
    @GetMapping("livrosDisponiveis")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDisponiveis() {
        return livros.getAll()
            .stream()
            .filter(l -> l.getCodigoUsuario() == -1)
            .toList();
    }

    @GetMapping("livrosEmprestados/{codigoUsuario}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosEmprestados(@PathVariable long codigoUsuario) {
        return livros.getAll()
                .stream()
                .filter(l -> l.getCodigoUsuario() == codigoUsuario)
                .toList();
    }

    @GetMapping("devolveLivro/{titulo}")
    @CrossOrigin(origins = "*")
    public String devolveLivro(@PathVariable(value = "titulo") String titulo) {
        Livro livro = livros.getLivroTitulo(titulo);
        if (livro != null) {
            livros.devolveLivro(livro.getId());
            return "Livro devolvido com sucesso :)";
        } else {
            return "Erro na devolução do livro!";
        }
    }

    @GetMapping("emprestaLivro/{titulo}/{codigoUsuario}")
    @CrossOrigin(origins = "*")
    public String emprestaLivro(@PathVariable(value = "titulo") String titulo, @PathVariable(value = "codigoUsuario") long codigoUsuario) {
        Livro livro = livros.getLivroTitulo(titulo);
        Usuario usuario = usuarios.getUsuario(codigoUsuario);
        if (livro != null && livro.getCodigoUsuario() == -1){
            if (usuario != null){
                livros.emprestaLivro(livro.getId(), usuario.getCodigo());
                return "Livro emprestado com sucesso :)";
            } else {
                return "Usuário não cadastrado";
            }
        } else {
            return "Livro Indisponível!";
        }
    }
}