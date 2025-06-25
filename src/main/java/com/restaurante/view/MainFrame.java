package com.restaurante.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    private final ClientePanel clientePanel;
    private final MesaPanel mesaPanel;
    private final ReservaPanel reservaPanel;
    private final AdministradorPanel adminPanel;
    private final RelatorioPanel relatorioPanel;

    public MainFrame() {
        setTitle("Sistema de Reservas de Restaurante");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializa os painéis
        clientePanel = new ClientePanel();
        mesaPanel = new MesaPanel();
        reservaPanel = new ReservaPanel();
        adminPanel = new AdministradorPanel();
        relatorioPanel = new RelatorioPanel();

        // Adiciona os painéis ao CardLayout
        cards.add(createMainPanel(), "Main");
        cards.add(clientePanel, "Clientes");
        cards.add(mesaPanel, "Mesas");
        cards.add(reservaPanel, "Reservas");
        cards.add(adminPanel, "Administradores");
        cards.add(relatorioPanel, "Relatorios");

        add(cards);

        // Mostra o painel principal inicialmente
        cardLayout.show(cards, "Main");
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Título
        JLabel lblTitulo = new JLabel("Sistema de Reservas de Restaurante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Botões
        JButton btnClientes = createMenuButton("Gerenciar Clientes");
        JButton btnMesas = createMenuButton("Gerenciar Mesas");
        JButton btnReservas = createMenuButton("Gerenciar Reservas");
        JButton btnAdmins = createMenuButton("Gerenciar Administradores");
        JButton btnRelatorios = createMenuButton("Relatórios");
        JButton btnSair = createMenuButton("Sair");

        btnClientes.addActionListener(e -> cardLayout.show(cards, "Clientes"));
        btnMesas.addActionListener(e -> cardLayout.show(cards, "Mesas"));
        btnReservas.addActionListener(e -> cardLayout.show(cards, "Reservas"));
        btnAdmins.addActionListener(e -> cardLayout.show(cards, "Administradores"));
        btnRelatorios.addActionListener(e -> cardLayout.show(cards, "Relatorios"));
        btnSair.addActionListener(e -> System.exit(0));

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(btnClientes, gbc);

        gbc.gridx = 1;
        panel.add(btnMesas, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(btnReservas, gbc);

        gbc.gridx = 1;
        panel.add(btnAdmins, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(btnRelatorios, gbc);

        gbc.gridx = 1;
        panel.add(btnSair, gbc);

        return panel;
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(300, 60));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}