package com.restaurante.service;

import com.restaurante.enums.Status;
import com.restaurante.enums.StatusMesa;  // Import adicionado
import com.restaurante.model.Cliente;
import com.restaurante.model.Mesa;
import com.restaurante.model.Reserva;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.ReservaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaService {
    private final ReservaRepository reservaRepo = new ReservaRepository();
    private final MesaRepository mesaRepo = new MesaRepository();

    public void realizarReserva(LocalDate data, LocalTime horario, int numPessoas,
                                Cliente cliente, Mesa mesa) {
        Reserva reserva = new Reserva(data, horario, numPessoas, Status.PENDENTE, cliente, mesa);
        reservaRepo.salvar(reserva);
        mesa.setStatus(StatusMesa.INDISPONIVEL);  // Corrigido para usar o enum
        mesaRepo.salvar(mesa);
    }

    public void confirmarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva != null) {
            reserva.setStatus(Status.CONFIRMADA);
            Mesa mesa = reserva.getMesa();
            mesa.setStatus(StatusMesa.RESERVADA);  // Atualiza status da mesa para RESERVADA
            reservaRepo.salvar(reserva);
            mesaRepo.salvar(mesa);
        }
    }

    public void cancelarReserva(int idReserva) {
        Reserva reserva = reservaRepo.buscarPorId(idReserva);
        if (reserva != null) {
            reserva.setStatus(Status.CANCELADA);
            Mesa mesa = reserva.getMesa();
            mesa.setStatus(StatusMesa.DISPONIVEL);  // Corrigido para usar o enum
            reservaRepo.salvar(reserva);
            mesaRepo.salvar(mesa);
        }
    }

    public List<Reserva> listarTodas() {
        return reservaRepo.listarTodas();
    }

    public List<Reserva> listarPorData(LocalDate data) {
        return reservaRepo.listarPorData(data);
    }

    public List<Reserva> listarPorStatus(Status status) {
        return reservaRepo.listarPorStatus(status);
    }

    public Reserva buscarPorId(int id) {
        return reservaRepo.buscarPorId(id);
    }
}