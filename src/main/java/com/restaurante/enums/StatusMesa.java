package com.restaurante.enums;

public enum StatusMesa {
    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível"),
    RESERVADA("Reservada"),
    EM_USO("Em Uso");

    private final String descricao;

    StatusMesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}