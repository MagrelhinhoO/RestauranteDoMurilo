package com.restaurante.service;

import com.restaurante.enums.StatusMesa;
import com.restaurante.model.Mesa;
import com.restaurante.repository.MesaRepository;
import java.util.List;

public class MesaService {
    private final MesaRepository repository = new MesaRepository();

    public void cadastrarMesa(int numero, int capacidade) {
        Mesa mesa = new Mesa(numero, capacidade, StatusMesa.DISPONIVEL);
        repository.salvar(mesa);
    }

    public Mesa buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Mesa> listarTodas() {
        return repository.listarTodas();
    }

    public List<Mesa> listarDisponiveis() {
        return repository.listarDisponiveis();
    }

    public void atualizarMesa(Mesa mesa) {
        repository.salvar(mesa);
    }

    public void removerMesa(int id) {
        repository.deletar(id);
    }

    public void liberarMesa(int idMesa) {
        Mesa mesa = repository.buscarPorId(idMesa);
        if (mesa != null) {
            mesa.setStatus(StatusMesa.DISPONIVEL);
            repository.salvar(mesa);
        }
    }

    public void ocuparMesa(int idMesa) {
        Mesa mesa = repository.buscarPorId(idMesa);
        if (mesa != null) {
            mesa.setStatus(StatusMesa.INDISPONIVEL);
            repository.salvar(mesa);
        }
    }
}