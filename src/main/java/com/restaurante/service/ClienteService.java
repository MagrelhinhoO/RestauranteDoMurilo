package com.restaurante.service;

import com.restaurante.model.Cliente;
import com.restaurante.repository.ClienteRepository;
import java.util.List;

public class ClienteService {
    private final ClienteRepository repository = new ClienteRepository();

    public void cadastrarCliente(String nome, String email, String telefone) {
        Cliente cliente = new Cliente(nome, email, telefone);
        repository.salvar(cliente);
    }

    public Cliente buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return repository.listarTodos();
    }

    public void atualizarCliente(Cliente cliente) {
        repository.salvar(cliente);
    }

    public void removerCliente(int id) {
        repository.deletar(id);
    }
}