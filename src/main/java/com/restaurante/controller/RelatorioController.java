package com.restaurante.controller;

import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import com.restaurante.service.RelatorioService;
import java.time.LocalDate;
import java.util.List;

public class RelatorioController {
    private final RelatorioService service = new RelatorioService();

    public List<Reserva> gerarRelatorioPorData(LocalDate data) {
        return service.gerarRelatorioPorData(data);
    }

    public List<Reserva> gerarRelatorioPorStatus(Status status) {
        return service.gerarRelatorioPorStatus(status);
    }

    public String gerarRelatorioOcupacao() {
        return service.gerarRelatorioOcupacao();
    }
}