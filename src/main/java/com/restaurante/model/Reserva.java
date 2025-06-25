package com.restaurante.model;

import com.restaurante.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private int numPessoas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    public Reserva() {}

    public Reserva(LocalDate data, LocalTime horario, int numPessoas, Status status, Cliente cliente, Mesa mesa) {
        this.data = data;
        this.horario = horario;
        this.numPessoas = numPessoas;
        this.status = status;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) {
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida ou no passado");
        }
        this.data = data;
    }

    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) {
        if (horario == null) {
            throw new IllegalArgumentException("Horário não pode ser nulo");
        }
        this.horario = horario;
    }

    public int getNumPessoas() { return numPessoas; }
    public void setNumPessoas(int numPessoas) {
        if (numPessoas <= 0) {
            throw new IllegalArgumentException("Número de pessoas deve ser positivo");
        }
        this.numPessoas = numPessoas;
    }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;
    }

    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa não pode ser nula");
        }
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "Reserva #" + id + " - " + data + " às " + horario +
                " (" + numPessoas + " pessoas) - Status: " + status +
                "\nCliente: " + cliente.getNome() +
                "\nMesa: " + mesa.getNumero();
    }
}