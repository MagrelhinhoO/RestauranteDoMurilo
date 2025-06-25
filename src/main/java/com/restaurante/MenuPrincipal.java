package com.restaurante;

import com.restaurante.view.AdministradorPanel;
import com.restaurante.view.ClientePanel;
import com.restaurante.view.MesaPanel;
import com.restaurante.view.RelatorioPanel;
import com.restaurante.view.ReservaPanel;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public MenuPrincipal() {
        setTitle("Sistema de Restaurante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Cria os painéis
        JPanel mainPanel = createMainPanel();
        ClientePanel clientePanel = new ClientePanel();
        AdministradorPanel administradorPanel = new AdministradorPanel();
        MesaPanel mesaPanel = new MesaPanel();
        ReservaPanel reservaPanel = new ReservaPanel();
        RelatorioPanel relatorioPanel = new RelatorioPanel();

        // Adiciona os painéis ao cardPanel
        cardPanel.add(mainPanel, "Main");
        cardPanel.add(clientePanel, "Clientes");
        cardPanel.add(administradorPanel, "Administradores");
        cardPanel.add(mesaPanel, "Mesas");
        cardPanel.add(reservaPanel, "Reservas");
        cardPanel.add(relatorioPanel, "Relatorios");

        add(cardPanel);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnClientes = new JButton("Clientes");
        JButton btnAdministradores = new JButton("Administradores");
        JButton btnMesas = new JButton("Mesas");
        JButton btnReservas = new JButton("Reservas");
        JButton btnRelatorios = new JButton("Relatórios");

        btnClientes.addActionListener(e -> cardLayout.show(cardPanel, "Clientes"));
        btnAdministradores.addActionListener(e -> cardLayout.show(cardPanel, "Administradores"));
        btnMesas.addActionListener(e -> cardLayout.show(cardPanel, "Mesas"));
        btnReservas.addActionListener(e -> cardLayout.show(cardPanel, "Reservas"));
        btnRelatorios.addActionListener(e -> cardLayout.show(cardPanel, "Relatorios"));

        panel.add(btnClientes);
        panel.add(btnAdministradores);
        panel.add(btnMesas);
        panel.add(btnReservas);
        panel.add(btnRelatorios);

        return panel;
    }

    public static void main(String[] args) {
        // Testa a conexão com o banco de dados antes de iniciar a interface
        try {
            com.restaurante.repository.JPAUtil.getEntityManager().close();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar ao banco de dados:\n" + e.getMessage(),
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MenuPrincipal().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}