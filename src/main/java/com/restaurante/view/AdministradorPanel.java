package com.restaurante.view;

import com.restaurante.controller.AdministradorController;
import com.restaurante.enums.TipoAdministrador;
import com.restaurante.model.Administrador;
import com.restaurante.model.Funcionario;
import com.restaurante.model.Gerente;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdministradorPanel extends JPanel {
    private final AdministradorController controller = new AdministradorController();
    private final JTable table;
    private final AdministradorTableModel tableModel;

    public AdministradorPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new AdministradorTableModel();
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        atualizarTabela();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        btnAdicionar.addActionListener(e -> adicionarAdministrador());
        btnEditar.addActionListener(e -> editarAdministrador());
        btnRemover.addActionListener(e -> removerAdministrador());
        btnVoltar.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "Main"));

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVoltar);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        List<Administrador> administradores = controller.listarTodos();
        tableModel.setAdministradores(administradores);
    }

    private void adicionarAdministrador() {
        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        JComboBox<TipoAdministrador> cbTipo = new JComboBox<>(TipoAdministrador.values());
        JTextField txtDetalhe = new JTextField();

        Object[] fields = {
                "Nome:", txtNome,
                "Email:", txtEmail,
                "Tipo:", cbTipo,
                "Cargo/Setor:", txtDetalhe
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Novo Administrador",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                TipoAdministrador tipo = (TipoAdministrador) cbTipo.getSelectedItem();
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String detalhe = txtDetalhe.getText();

                switch (tipo) {
                    case GERENTE:
                        controller.cadastrarGerente(nome, email, detalhe);
                        break;
                    case FUNCIONARIO:
                        controller.cadastrarFuncionario(nome, email, detalhe);
                        break;
                    case ADMINISTRADOR:
                        controller.cadastrarAdministrador(nome, email, tipo);
                        break;
                }
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarAdministrador() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um administrador para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Administrador admin = tableModel.getAdministradorAt(selectedRow);

        JTextField txtNome = new JTextField(admin.getNome());
        JTextField txtEmail = new JTextField(admin.getEmail());
        JTextField txtDetalhe = new JTextField();

        TipoAdministrador tipo = admin.getTipo();
        if (admin instanceof Gerente) {
            txtDetalhe.setText(((Gerente) admin).getSetor());
        } else if (admin instanceof Funcionario) {
            txtDetalhe.setText(((Funcionario) admin).getCargo());
        }

        JComboBox<TipoAdministrador> cbTipo = new JComboBox<>(TipoAdministrador.values());
        cbTipo.setSelectedItem(tipo);
        cbTipo.setEnabled(false); // Não permitir mudar o tipo ao editar

        Object[] fields = {
                "Nome:", txtNome,
                "Email:", txtEmail,
                "Tipo:", cbTipo,
                "Cargo/Setor:", txtDetalhe
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Editar Administrador",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                admin.setNome(txtNome.getText());
                admin.setEmail(txtEmail.getText());

                if (admin instanceof Gerente) {
                    ((Gerente) admin).setSetor(txtDetalhe.getText());
                } else if (admin instanceof Funcionario) {
                    ((Funcionario) admin).setCargo(txtDetalhe.getText());
                }

                controller.atualizar(admin);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerAdministrador() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um administrador para remover", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover este administrador?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Administrador admin = tableModel.getAdministradorAt(selectedRow);
            controller.remover(admin.getId());
            atualizarTabela();
        }
    }
}