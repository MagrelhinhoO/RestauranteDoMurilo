package com.restaurante.view;

import com.restaurante.controller.MesaController;
import com.restaurante.enums.StatusMesa;
import com.restaurante.model.Mesa;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MesaPanel extends JPanel {
    private final MesaController controller = new MesaController();
    private final JTable table;
    private final MesaTableModel tableModel;

    public MesaPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new MesaTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        atualizarTabela();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnLiberar = new JButton("Liberar");
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        btnAdicionar.addActionListener(e -> adicionarMesa());
        btnEditar.addActionListener(e -> editarMesa());
        btnLiberar.addActionListener(e -> liberarMesa());
        btnRemover.addActionListener(e -> removerMesa());
        btnVoltar.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "Main"));

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnLiberar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVoltar);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        List<Mesa> mesas = controller.listarTodasMesas();
        tableModel.setMesas(mesas);
    }

    private void adicionarMesa() {
        JSpinner spnNumero = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner spnCapacidade = new JSpinner(new SpinnerNumberModel(4, 1, 20, 1));

        Object[] fields = {
                "Número:", spnNumero,
                "Capacidade:", spnCapacidade
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Nova Mesa",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                controller.cadastrarMesa(
                        (Integer) spnNumero.getValue(),
                        (Integer) spnCapacidade.getValue());
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarMesa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma mesa para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mesa mesa = tableModel.getMesaAt(selectedRow);

        JSpinner spnNumero = new JSpinner(new SpinnerNumberModel(mesa.getNumero(), 1, 100, 1));
        JSpinner spnCapacidade = new JSpinner(new SpinnerNumberModel(mesa.getCapacidade(), 1, 20, 1));

        Object[] fields = {
                "Número:", spnNumero,
                "Capacidade:", spnCapacidade
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Editar Mesa",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                mesa.setNumero((Integer) spnNumero.getValue());
                mesa.setCapacidade((Integer) spnCapacidade.getValue());
                controller.atualizarMesa(mesa);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void liberarMesa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma mesa para liberar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mesa mesa = tableModel.getMesaAt(selectedRow);
        controller.liberarMesa(mesa.getId());
        atualizarTabela();
    }

    private void removerMesa() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma mesa para remover", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover esta mesa?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Mesa mesa = tableModel.getMesaAt(selectedRow);
            controller.removerMesa(mesa.getId());
            atualizarTabela();
        }
    }
}