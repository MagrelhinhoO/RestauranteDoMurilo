package com.restaurante.view;

import com.restaurante.model.Mesa;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MesaTableModel extends AbstractTableModel {
    private List<Mesa> mesas = new ArrayList<>();
    private final String[] colunas = {"ID", "Número", "Capacidade", "Status"};

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
        fireTableDataChanged();
    }

    public Mesa getMesaAt(int rowIndex) {
        return mesas.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return mesas.size();
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
        Mesa mesa = mesas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> mesa.getId();
            case 1 -> mesa.getNumero();
            case 2 -> mesa.getCapacidade();
            case 3 -> mesa.getStatus();
            default -> null;
        };
    }
}