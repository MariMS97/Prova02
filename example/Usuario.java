package org.example;

class Usuario {
    private String iniciais;
    private String senha;
    private String nome;
    private String sobrenome;
    private String email;

    public Usuario(String iniciais, String senha, String nome, String sobrenome, String email) {
        this.iniciais = iniciais;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public String getIniciais() {
        return iniciais;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }
}