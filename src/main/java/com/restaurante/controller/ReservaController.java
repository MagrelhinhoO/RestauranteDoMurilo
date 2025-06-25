package com.restaurante.controller;

import com.restaurante.enums.Status;
import com.restaurante.model.*;
import com.restaurante.service.ReservaService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaController {
    private final ReservaService service = new ReservaService();

    public void realizarReserva(LocalDate data, LocalTime horario, int numPessoas,
                                Cliente cliente, Mesa mesa) {
        service.realizarReserva(data, horario, numPessoas, cliente, mesa);
    }

    public void confirmarReserva(int id) {
        service.confirmarReserva(id);
    }

    public void cancelarReserva(int id) {
        service.cancelarReserva(id);
    }

    public Reserva buscarReservaPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Reserva> listarTodasReservas() {
        return service.listarTodas();
    }

    public List<Reserva> listarReservasPorData(LocalDate data) {
        return service.listarPorData(data);
    }

    public List<Reserva> listarReservasPorStatus(Status status) {
        return service.listarPorStatus(status);
    }
}