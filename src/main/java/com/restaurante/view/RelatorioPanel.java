package com.restaurante.view;

import com.restaurante.controller.RelatorioController;
import com.restaurante.enums.Status;
import com.restaurante.model.Reserva;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class RelatorioPanel extends JPanel {
    private final RelatorioController controller = new RelatorioController();
    private final JTextArea txtRelatorio = new JTextArea(15, 50);

    public RelatorioPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtRelatorio.setEditable(false);
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnReservasData = new JButton("Reservas por Data");
        JButton btnReservasStatus = new JButton("Reservas por Status");
        JButton btnOcupacao = new JButton("Ocupação de Mesas");
        JButton btnVoltar = new JButton("Voltar");

        btnReservasData.addActionListener(e -> gerarRelatorioReservasPorData());
        btnReservasStatus.addActionListener(e -> gerarRelatorioReservasPorStatus());
        btnOcupacao.addActionListener(e -> gerarRelatorioOcupacao());
        btnVoltar.addActionListener(e -> ((CardLayout) getParent().getLayout()).show(getParent(), "Main"));

        buttonPanel.add(btnReservasData);
        buttonPanel.add(btnReservasStatus);
        buttonPanel.add(btnOcupacao);
        buttonPanel.add(btnVoltar);

        add(new JScrollPane(txtRelatorio), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void gerarRelatorioReservasPorData() {
        JSpinner spnData = new JSpinner(new SpinnerDateModel());
        spnData.setEditor(new JSpinner.DateEditor(spnData, "dd/MM/yyyy"));

        int option = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Data:", spnData},
                "Selecione a Data",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            LocalDate data = ((Date) spnData.getValue()).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            List<Reserva> reservas = controller.gerarRelatorioPorData(data);
            StringBuilder sb = new StringBuilder();
            sb.append("=== RESERVAS PARA ").append(data).append(" ===\n\n");

            if (reservas.isEmpty()) {
                sb.append("Nenhuma reserva encontrada para esta data.\n");
            } else {
                reservas.forEach(reserva -> sb.append(formatReserva(reserva)).append("\n"));
            }

            sb.append("\nTotal: ").append(reservas.size()).append(" reserva(s)");
            txtRelatorio.setText(sb.toString());
        }
    }

    private void gerarRelatorioReservasPorStatus() {
        Status[] statusOptions = Status.values();
        Status selectedStatus = (Status) JOptionPane.showInputDialog(
                this,
                "Selecione o status:",
                "Reservas por Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                statusOptions,
                statusOptions[0]);

        if (selectedStatus != null) {
            List<Reserva> reservas = controller.gerarRelatorioPorStatus(selectedStatus);
            StringBuilder sb = new StringBuilder();
            sb.append("=== RESERVAS ").append(selectedStatus.getDescricao()).append(" ===\n\n");

            if (reservas.isEmpty()) {
                sb.append("Nenhuma reserva com este status.\n");
            } else {
                reservas.forEach(reserva -> sb.append(formatReserva(reserva)).append("\n"));
            }

            sb.append("\nTotal: ").append(reservas.size()).append(" reserva(s)");
            txtRelatorio.setText(sb.toString());
        }
    }

    private void gerarRelatorioOcupacao() {
        String relatorio = controller.gerarRelatorioOcupacao();
        txtRelatorio.setText(relatorio);
    }

    private String formatReserva(Reserva reserva) {
        return String.format(
                "Reserva #%d - %s às %s (%d pessoas)\n" +
                        "Cliente: %s\n" +
                        "Mesa: %d - Status: %s\n",
                reserva.getId(),
                reserva.getData(),
                reserva.getHorario(),
                reserva.getNumPessoas(),
                reserva.getCliente().getNome(),
                reserva.getMesa().getNumero(),
                reserva.getStatus());
    }
}