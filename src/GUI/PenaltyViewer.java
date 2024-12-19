package GUI;

import Classes.Penalty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PenaltyViewer {

    private JFrame frame;
    private JTable penaltyTable;
    private DefaultTableModel tableModel;

    public PenaltyViewer(List<Penalty> penalties) {
        frame = new JFrame("Penalty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columnNames = {"Penalty ID", "Description", "Amount", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        penaltyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(penaltyTable);

        if (!penalties.isEmpty()) {
            for (Penalty penalty : penalties) {
                Object[] rowData = {
                        penalty.getPenaltyId(),
                        penalty.getDescription(),
                        penalty.getAmount(),
                        penalty.getDate()
                };
                tableModel.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No penalties found", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton closeButton = new JButton("OK");
        closeButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(closeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
