package GUI;

import Classes.TeamLeader;
import Classes.Employee;
import Classes.Penalty;
import Classes.VacationRequest;
import Enum.VacationStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageEmp {
    private TeamLeader teamLeader;
    private JFrame frame;
    private JTable vacationTable;
    private JComboBox<String> penaltyEmployeeComboBox;
    private JTextField penaltyDescriptionField;
    private JTextField penaltyAmountField;

    public ManageEmp(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Manage Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Vacation Requests", createVacationManagementPanel());
        tabbedPane.addTab("Penalties", createPenaltyManagementPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createVacationManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table for vacation requests
        String[] columnNames = {"Request ID", "User", "Start Date", "End Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        vacationTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(vacationTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Form Panel for Approve/Reject
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Manage Request"));

        JLabel requestIdLabel = new JLabel("Request ID:");
        JTextField requestIdField = new JTextField();
        formPanel.add(requestIdLabel);
        formPanel.add(requestIdField);

        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(e -> processVacationRequest(requestIdField, VacationStatus.Approved));
        formPanel.add(approveButton);

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> processVacationRequest(requestIdField, VacationStatus.Rejected));
        formPanel.add(rejectButton);

        panel.add(formPanel, BorderLayout.SOUTH);

        // Buttons Panel for Table Management
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton refreshButton = new JButton("Refresh Requests");
        refreshButton.addActionListener(e -> displayVacationRequests(model));
        buttonPanel.add(refreshButton);

        panel.add(buttonPanel, BorderLayout.NORTH);

        // Initial Load
        displayVacationRequests(model);

        return panel;
    }

    private void processVacationRequest(JTextField requestIdField, VacationStatus newStatus) {
        try {
            int requestId = Integer.parseInt(requestIdField.getText());
            List<Employee> employees = teamLeader.getAllEmployees();

            boolean requestFound = false;
            for (Employee employee : employees) {
                for (VacationRequest request : employee.getVacationRequests()) {
                    if (request.getRequestId() == requestId) {
                        teamLeader.manageVacation(employee.getUserId(), requestId, newStatus);
                        JOptionPane.showMessageDialog(frame, "Request " + requestId + " " + newStatus.name() + " successfully.");
                        requestIdField.setText("");
                        displayVacationRequests((DefaultTableModel) vacationTable.getModel());
                        requestFound = true;
                        break;
                    }
                }
                if (requestFound) break;
            }

            if (!requestFound) {
                JOptionPane.showMessageDialog(frame, "Request ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid Request ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error processing request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private JPanel createPenaltyManagementPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Select Employee:"));
        penaltyEmployeeComboBox = new JComboBox<>();
        populatePenaltyEmployeeComboBox();
        panel.add(penaltyEmployeeComboBox);

        panel.add(new JLabel("Description:"));
        penaltyDescriptionField = new JTextField();
        panel.add(penaltyDescriptionField);

        panel.add(new JLabel("Amount:"));
        penaltyAmountField = new JTextField();
        panel.add(penaltyAmountField);

        JButton addPenaltyButton = new JButton("Add Penalty");
        addPenaltyButton.addActionListener(e -> addPenalty());
        panel.add(addPenaltyButton);

        return panel;
    }

    private void displayVacationRequests(DefaultTableModel model) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                model.setRowCount(0); // Clear existing rows
                List<Employee> employees = teamLeader.getAllEmployees();
                for (Employee employee : employees) {
                    if (employee.getVacationRequests() != null) {
                        for (VacationRequest request : employee.getVacationRequests()) {
                            model.addRow(new Object[]{
                                    request.getRequestId(),
                                    employee.getName(),
                                    request.getStartDate(),
                                    request.getEndDate(),
                                    request.getStatus()
                            });
                        }
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                JOptionPane.showMessageDialog(frame, "Vacation requests loaded.");
            }
        }.execute();
    }

    private void manageVacations(DefaultTableModel model) {
        try {
            for (int i = 0; i < model.getRowCount(); i++) {
                int requestId = (int) model.getValueAt(i, 0);
                String userName = (String) model.getValueAt(i, 1);
                VacationStatus status = VacationStatus.valueOf(model.getValueAt(i, 2).toString());

                Employee employee = teamLeader.getEmployeeByName(userName);
                if (employee != null) {
                    teamLeader.manageVacation(employee.getUserId(), requestId, status);
                }
            }
            JOptionPane.showMessageDialog(frame, "Vacation requests updated successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error updating vacation requests: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populatePenaltyEmployeeComboBox() {
        List<Employee> employees = teamLeader.getAllEmployees();
        for (Employee employee : employees) {
            penaltyEmployeeComboBox.addItem(employee.getName());
        }
    }

    private void addPenalty() {
        try {
            String selectedEmployeeName = (String) penaltyEmployeeComboBox.getSelectedItem();
            Employee employee = teamLeader.getEmployeeByName(selectedEmployeeName);

            if (employee != null) {
                String description = penaltyDescriptionField.getText();
                double amount = Double.parseDouble(penaltyAmountField.getText());

                Penalty penalty = new Penalty(description, amount);
                teamLeader.addPenalty(employee.getUserId(), penalty);

                penaltyDescriptionField.setText("");
                penaltyAmountField.setText("");

                JOptionPane.showMessageDialog(frame, "Penalty added successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input for penalty amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        TeamLeader teamLeader = new TeamLeader("jane.smith@example.com", "password456");
        if (teamLeader.login()) {
            SwingUtilities.invokeLater(() -> new ManageEmp(teamLeader));
        } else {
            System.out.println("Login failed.");
        }
    }
}
