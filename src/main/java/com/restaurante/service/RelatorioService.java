package com.restaurante.service;

import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import com.restaurante.model.Mesa;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.ReservaRepository;
import java.time.LocalDate;
import java.util.List;

public class RelatorioService {
    private final ReservaRepository reservaRepo;
    private final MesaRepository mesaRepo;

    // Construtor para injeção de dependências
    public RelatorioService() {
        this.reservaRepo = new ReservaRepository();
        this.mesaRepo = new MesaRepository();
    }

    // Método alternativo caso queira injetar os repositórios externamente
    public RelatorioService(ReservaRepository reservaRepo, MesaRepository mesaRepo) {
        this.reservaRepo = reservaRepo;
        this.mesaRepo = mesaRepo;
    }

    public List<Reserva> gerarRelatorioPorData(LocalDate data) {
        return reservaRepo.listarPorData(data);
    }

    public List<Reserva> gerarRelatorioPorStatus(Status status) {
        return reservaRepo.listarPorStatus(status);
    }

    public String gerarRelatorioOcupacao() {
        // Obter o total de mesas
        List<Mesa> todasMesas = mesaRepo.listarTodas();
        int totalMesas = todasMesas.size();

        // Obter reservas confirmadas para hoje
        List<Reserva> reservasConfirmadas = reservaRepo.listarPorDataEStatus(
                LocalDate.now(),
                Status.CONFIRMADA
        );
        int mesasOcupadas = reservasConfirmadas.size();

        // Calcular porcentagem de ocupação (evitando divisão por zero)
        double ocupacao = totalMesas > 0 ? (double) mesasOcupadas / totalMesas * 100 : 0;

        return String.format(
                "Relatório de Ocupação:\n" +
                        "Total de mesas: %d\n" +
                        "Mesas ocupadas hoje: %d\n" +
                        "Taxa de ocupação: %.2f%%\n",
                totalMesas, mesasOcupadas, ocupacao
        );
    }
}