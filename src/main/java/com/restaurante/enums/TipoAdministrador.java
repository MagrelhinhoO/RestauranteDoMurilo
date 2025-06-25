package com.restaurante.enums;

public enum TipoAdministrador {
    GERENTE("Gerente"),
    FUNCIONARIO("Funcionário"),
    ADMINISTRADOR("Administrador Geral");

    private final String descricao;

    TipoAdministrador(String descricao) {
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