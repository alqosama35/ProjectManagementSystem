package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class ManageEmp {

    private JFrame frame;
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField jobField;
    private JTextField salaryField;
    private JTable employeeTable;
    private JButton addButton;

    public ManageEmp() {
        // Initialize the frame and main panel
        frame = new JFrame("Employee Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        mainPanel = new JPanel(new BorderLayout());

        // Input panel for adding employee
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Job Title:"));
        jobField = new JTextField();
        inputPanel.add(jobField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        // Button to add employee
        addButton = new JButton("Add Employee");
        inputPanel.add(addButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Table to display employees
        String[] columnNames = {"Employee ID", "Name", "Job Title", "Salary"};
        Object[][] data = {};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Action Listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String jobTitle = jobField.getText();
                String salary = salaryField.getText();

                // Add the employee to the table (simplified example)
                // You can extend this with database functionality later
                if (!name.isEmpty() && !jobTitle.isEmpty() && !salary.isEmpty()) {
                    Object[] row = {employeeTable.getRowCount() + 1, name, jobTitle, salary};
                    tableModel.addRow(row);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                }
            }
        });

        // Add the main panel to the frame
        frame.add(mainPanel);
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ManageEmp manageEmp = new ManageEmp();
                manageEmp.showGUI();
            }
        });
    }
}
