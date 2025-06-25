package com.restaurante.controller;

import com.restaurante.enums.StatusMesa;
import com.restaurante.model.Mesa;
import com.restaurante.service.MesaService;
import java.util.List;

public class MesaController {
    private final MesaService service = new MesaService();

    public void cadastrarMesa(int numero, int capacidade) {
        service.cadastrarMesa(numero, capacidade);
    }

    public Mesa buscarMesaPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Mesa> listarTodasMesas() {
        return service.listarTodas();
    }

    public List<Mesa> listarMesasDisponiveis() {
        return service.listarDisponiveis();
    }

    public void atualizarMesa(Mesa mesa) {
        service.atualizarMesa(mesa);
    }

    public void removerMesa(int id) {
        service.removerMesa(id);
    }

    public void liberarMesa(int id) {
        service.liberarMesa(id);
    }

    public void ocuparMesa(int id) {
        service.ocuparMesa(id);
    }
}