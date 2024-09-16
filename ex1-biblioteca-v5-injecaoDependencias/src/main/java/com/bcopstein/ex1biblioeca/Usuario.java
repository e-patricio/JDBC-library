package com.bcopstein.ex1biblioeca;

public class Usuario {
    private long codigo;
    private String nome;
    private int anoNasc;

    public Usuario(long codigo, String nome, int anoNasc) {
        this.codigo = codigo;
        this.nome = nome;
        this.anoNasc = anoNasc;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    @Override
    public String toString() {
        return "Usuario [anoNasc=" + anoNasc + ", codigo=" + codigo + ", nome=" + nome + "]";
    }
}
