package com.restaurante.controller;

import com.restaurante.enums.TipoAdministrador;
import com.restaurante.model.Administrador;
import com.restaurante.model.Funcionario;
import com.restaurante.model.Gerente;
import com.restaurante.service.AdministradorService;
import java.util.List;

public class AdministradorController {
    private final AdministradorService service = new AdministradorService();

    public void cadastrarAdministrador(String nome, String email, TipoAdministrador tipo) {
        service.cadastrarAdministrador(nome, email, tipo);
    }

    public void cadastrarFuncionario(String nome, String email, String cargo) {
        service.cadastrarFuncionario(nome, email, cargo);
    }

    public void cadastrarGerente(String nome, String email, String setor) {
        service.cadastrarGerente(nome, email, setor);
    }

    public Administrador buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public List<Administrador> listarTodos() {
        return service.listarTodos();
    }

    public void atualizar(Administrador admin) {
        service.atualizar(admin);
    }

    public void remover(int id) {
        service.remover(id);
    }
}
