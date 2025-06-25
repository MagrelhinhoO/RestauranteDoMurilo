package com.restaurante.view;

import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientePanel extends JPanel {
    private final ClienteService service = new ClienteService();
    private final JTable table;
    private final ClienteTableModel tableModel;

    public ClientePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Modelo da tabela
        tableModel = new ClienteTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Atualiza os dados
        atualizarTabela();

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        btnAdicionar.addActionListener(e -> adicionarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnRemover.addActionListener(e -> removerCliente());
        btnVoltar.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "Main"));

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVoltar);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        List<Cliente> clientes = service.listarTodos();
        tableModel.setClientes(clientes);
    }

    private void adicionarCliente() {
        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelefone = new JTextField();

        Object[] fields = {
                "Nome:", txtNome,
                "Email:", txtEmail,
                "Telefone:", txtTelefone
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Novo Cliente",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                service.cadastrarCliente(
                        txtNome.getText(),
                        txtEmail.getText(),
                        txtTelefone.getText());
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarCliente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = tableModel.getClienteAt(selectedRow);

        JTextField txtNome = new JTextField(cliente.getNome());
        JTextField txtEmail = new JTextField(cliente.getEmail());
        JTextField txtTelefone = new JTextField(cliente.getTelefone());

        Object[] fields = {
                "Nome:", txtNome,
                "Email:", txtEmail,
                "Telefone:", txtTelefone
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Editar Cliente",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                cliente.setNome(txtNome.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtTelefone.getText());
                service.atualizarCliente(cliente);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerCliente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para remover", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover este cliente?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Cliente cliente = tableModel.getClienteAt(selectedRow);
            service.removerCliente(cliente.getId());
            atualizarTabela();
        }
    }
}