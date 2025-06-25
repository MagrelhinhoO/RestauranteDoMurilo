package com.restaurante.model;

import com.restaurante.enums.TipoAdministrador;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "administradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(nullable = false, length = 100)
    protected String nome;

    @Column(nullable = false, unique = true, length = 100)
    protected String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    protected TipoAdministrador tipo;

    public Administrador() {
        this.tipo = TipoAdministrador.ADMINISTRADOR;
    }

    public Administrador(String nome, String email, TipoAdministrador tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        this.email = email;
    }

    public TipoAdministrador getTipo() { return tipo; }
    public void setTipo(TipoAdministrador tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo não pode ser nulo");
        }
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome + " (" + email + ") - " + tipo.getDescricao();
    }
}