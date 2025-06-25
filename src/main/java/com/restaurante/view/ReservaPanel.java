package com.restaurante.view;

import com.restaurante.controller.ClienteController;
import com.restaurante.controller.MesaController;
import com.restaurante.controller.ReservaController;
import com.restaurante.enums.Status;
import com.restaurante.model.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaPanel extends JPanel {
    private final ReservaController reservaController = new ReservaController();
    private final ClienteController clienteController = new ClienteController();
    private final MesaController mesaController = new MesaController();

    private final JTable table;
    private final ReservaTableModel tableModel;

    public ReservaPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new ReservaTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        atualizarTabela();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnNova = new JButton("Nova Reserva");
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        btnNova.addActionListener(e -> novaReserva());
        btnConfirmar.addActionListener(e -> confirmarReserva());
        btnCancelar.addActionListener(e -> cancelarReserva());
        btnRemover.addActionListener(e -> removerReserva());
        btnVoltar.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "Main"));

        buttonPanel.add(btnNova);
        buttonPanel.add(btnConfirmar);
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVoltar);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        List<Reserva> reservas = reservaController.listarTodasReservas();
        tableModel.setReservas(reservas);
    }

    private void novaReserva() {
        // Obter lista de clientes e mesas disponíveis
        List<Cliente> clientes = clienteController.listarTodosClientes();
        List<Mesa> mesasDisponiveis = mesaController.listarMesasDisponiveis();

        // Criar modelos para os comboboxes
        DefaultComboBoxModel<Cliente> clienteModel = new DefaultComboBoxModel<>();
        clientes.forEach(clienteModel::addElement);

        DefaultComboBoxModel<Mesa> mesaModel = new DefaultComboBoxModel<>();
        mesasDisponiveis.forEach(mesaModel::addElement);

        // Criar componentes do formulário
        JComboBox<Cliente> cbCliente = new JComboBox<>(clienteModel);
        JComboBox<Mesa> cbMesa = new JComboBox<>(mesaModel);
        JSpinner spnPessoas = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
        JSpinner spnData = new JSpinner(new SpinnerDateModel());
        JSpinner spnHora = new JSpinner(new SpinnerDateModel());

        // Configurar formatos dos spinners de data e hora
        spnData.setEditor(new JSpinner.DateEditor(spnData, "dd/MM/yyyy"));
        spnHora.setEditor(new JSpinner.DateEditor(spnHora, "HH:mm"));

        Object[] fields = {
                "Cliente:", cbCliente,
                "Mesa:", cbMesa,
                "Número de Pessoas:", spnPessoas,
                "Data:", spnData,
                "Hora:", spnHora
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Nova Reserva",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                Cliente cliente = (Cliente) cbCliente.getSelectedItem();
                Mesa mesa = (Mesa) cbMesa.getSelectedItem();
                int numPessoas = (Integer) spnPessoas.getValue();
                LocalDate data = LocalDate.ofInstant(
                        ((java.util.Date) spnData.getValue()).toInstant(),
                        java.time.ZoneId.systemDefault());
                LocalTime hora = LocalTime.ofInstant(
                        ((java.util.Date) spnHora.getValue()).toInstant(),
                        java.time.ZoneId.systemDefault());

                reservaController.realizarReserva(data, hora, numPessoas, cliente, mesa);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void confirmarReserva() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva para confirmar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Reserva reserva = tableModel.getReservaAt(selectedRow);
        reservaController.confirmarReserva(reserva.getId());
        atualizarTabela();
    }

    private void cancelarReserva() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva para cancelar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Reserva reserva = tableModel.getReservaAt(selectedRow);
        reservaController.cancelarReserva(reserva.getId());
        atualizarTabela();
    }

    private void removerReserva() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva para remover", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover esta reserva?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Reserva reserva = tableModel.getReservaAt(selectedRow);
            reservaController.cancelarReserva(reserva.getId());
            atualizarTabela();
        }
    }
}