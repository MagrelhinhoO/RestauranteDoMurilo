package com.restaurante.model;

import com.restaurante.enums.TipoAdministrador;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Administrador {
    @Column(nullable = false, length = 50)
    private String cargo;

    public Funcionario() {
        super();
        this.tipo = TipoAdministrador.FUNCIONARIO;
    }

    public Funcionario(String nome, String email, String cargo) {
        super(nome, email, TipoAdministrador.FUNCIONARIO);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo não pode ser vazio");
        }
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString() + " - Cargo: " + cargo;
    }
}