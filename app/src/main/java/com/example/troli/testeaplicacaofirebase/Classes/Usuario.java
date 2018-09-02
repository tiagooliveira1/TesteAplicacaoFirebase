package com.example.troli.testeaplicacaofirebase.Classes;

import java.util.Date;

/**
 * Created by troli on 27/08/2018.
 */

public class Usuario {
    private String nome;
    private Date dataNascimento;

    private int nivelGraduacao;
    private String idioma;

    public Usuario(String nome, Date dataNascimento, int nivelGraduacao, String idioma) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelGraduacao = nivelGraduacao;
        this.idioma = idioma;
    }

    public Usuario() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNivelGraduacao() {
        return nivelGraduacao;
    }

    public void setNivelGraduacao(int nivelGraduacao) {
        this.nivelGraduacao = nivelGraduacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
