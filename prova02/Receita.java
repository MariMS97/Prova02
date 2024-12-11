package org.example;

class Receita {
    private String nome;
    private String ingredientes;
    private String modoPreparo;
    private String usuarioIniciais;

    public Receita(String nome, String ingredientes, String modoPreparo, String usuarioIniciais) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.modoPreparo = modoPreparo;
        this.usuarioIniciais = usuarioIniciais;
    }

    public String getNome() {
        return nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public String getUsuarioIniciais() {
        return usuarioIniciais;
    }
}
