package com.example.poobdn1.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Contato {
    private String nome, dataNasc, CPF, telefone1, telefone2, email, endereco;

    public Contato() {
        // vazio
    }

    public Contato(String nome, String dataNasc, String CPF,
                   String telefone1, String telefone2, String email,
                   String endereco) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.CPF = CPF;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), " nome: %s | email: %s | CPF: %s", nome, email, CPF);
    }
}
