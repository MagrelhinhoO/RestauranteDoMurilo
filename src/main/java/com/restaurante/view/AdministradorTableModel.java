package com.restaurante.view;

import com.restaurante.model.Administrador;
import com.restaurante.model.Funcionario;
import com.restaurante.model.Gerente;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AdministradorTableModel extends AbstractTableModel {
    private List<Administrador> administradores = new ArrayList<>();
    private final String[] colunas = {"ID", "Nome", "Email", "Tipo", "Detalhe"};

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
        fireTableDataChanged();
    }

    public Administrador getAdministradorAt(int rowIndex) {
        return administradores.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return administradores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Administrador admin = administradores.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> admin.getId();
            case 1 -> admin.getNome();
            case 2 -> admin.getEmail();
            case 3 -> admin.getTipo().getDescricao();
            case 4 -> getDetalheAdministrador(admin);
            default -> null;
        };
    }

    private String getDetalheAdministrador(Administrador admin) {
        if (admin instanceof Gerente) return ((Gerente) admin).getSetor();
        if (admin instanceof Funcionario) return ((Funcionario) admin).getCargo();
        return "N/A";
    }
}