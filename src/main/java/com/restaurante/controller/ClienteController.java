package com.restaurante.controller;

import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;
import java.util.List;

public class ClienteController {
    private final ClienteService service = new ClienteService();

    public void cadastrarCliente(String nome, String email, String telefone) {
        service.cadastrarCliente(nome, email, telefone);
    }

    public Cliente buscarClientePorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Cliente> listarTodosClientes() {
        return service.listarTodos();
    }

    public void atualizarCliente(Cliente cliente) {
        service.atualizarCliente(cliente);
    }

    public void removerCliente(int id) {
        service.removerCliente(id);
    }
}