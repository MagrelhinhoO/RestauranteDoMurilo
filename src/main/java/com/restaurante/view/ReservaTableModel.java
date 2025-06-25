package com.restaurante.view;

import com.restaurante.model.Reserva;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservaTableModel extends AbstractTableModel {
    private List<Reserva> reservas = new ArrayList<>();
    private final String[] colunas = {"ID", "Data", "Hora", "Pessoas", "Cliente", "Mesa", "Status"};
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
        fireTableDataChanged();
    }

    public Reserva getReservaAt(int rowIndex) {
        return reservas.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return reservas.size();
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
        Reserva reserva = reservas.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> reserva.getId();
            case 1 -> reserva.getData().format(dateFormatter);
            case 2 -> reserva.getHorario().format(timeFormatter);
            case 3 -> reserva.getNumPessoas();
            case 4 -> reserva.getCliente().getNome();
            case 5 -> reserva.getMesa().getNumero();
            case 6 -> reserva.getStatus();
            default -> null;
        };
    }
}