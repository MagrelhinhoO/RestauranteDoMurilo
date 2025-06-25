package com.restaurante.service;

import com.restaurante.enums.TipoAdministrador;
import com.restaurante.model.Administrador;
import com.restaurante.model.Funcionario;
import com.restaurante.model.Gerente;
import com.restaurante.repository.AdministradorRepository;
import java.util.List;

public class AdministradorService {
    private final AdministradorRepository repository = new AdministradorRepository();

    public void cadastrarAdministrador(String nome, String email, TipoAdministrador tipo) {
        Administrador admin = new Administrador(nome, email, tipo);
        repository.salvar(admin);
    }

    public void cadastrarFuncionario(String nome, String email, String cargo) {
        Funcionario funcionario = new Funcionario(nome, email, cargo);
        repository.salvar(funcionario);
    }

    public void cadastrarGerente(String nome, String email, String setor) {
        Gerente gerente = new Gerente(nome, email, setor);
        repository.salvar(gerente);
    }

    public Administrador buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Administrador> listarTodos() {
        return repository.listarTodos();
    }

    public void atualizar(Administrador admin) {
        repository.salvar(admin);
    }

    public void remover(int id) {
        repository.deletar(id);
    }
}