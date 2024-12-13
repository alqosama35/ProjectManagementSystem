package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VA_Tasks {
    private JPanel mainPanel;
    private JTable taskTable;
    private JTextField taskField;
    private JComboBox<String> assignComboBox;
    private JTextField deadlineField;
    private JButton addButton;

    public VA_Tasks() {
        // Initialize components
        mainPanel = new JPanel();
        taskTable = new JTable();
        taskField = new JTextField();
        assignComboBox = new JComboBox<>();
        deadlineField = new JTextField();
        addButton = new JButton("Add Task");

        // Set up the task list (add dummy names for assignment options)
        assignComboBox.addItem("John");
        assignComboBox.addItem("Mary");
        assignComboBox.addItem("Alice");
        assignComboBox.addItem("Bob");

        // Set up the table model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Task", "Assigned To", "Deadline"}, 0);
        taskTable.setModel(tableModel);

        // Layout setup
        mainPanel.setLayout(new BorderLayout());

        // Create a panel for inputs and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        // Add task input field
        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskField);

        // Add assignment dropdown
        inputPanel.add(new JLabel("Assign To:"));
        inputPanel.add(assignComboBox);

        // Add deadline input field
        inputPanel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        inputPanel.add(deadlineField);

        // Add input panel to the main panel (top part)
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Add table to the center of the main panel
        mainPanel.add(new JScrollPane(taskTable), BorderLayout.CENTER);

        // Add button panel (with Add Button)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add button action listener using a lambda
        addButton.addActionListener(e -> {
            String task = taskField.getText();
            String assignedTo = (String) assignComboBox.getSelectedItem();
            String deadline = deadlineField.getText();

            // Validate input fields
            if (!task.isEmpty() && !deadline.isEmpty()) {
                tableModel.addRow(new Object[]{task, assignedTo, deadline});
                taskField.setText(""); // Clear task input field
                assignComboBox.setSelectedIndex(0); // Reset dropdown
                deadlineField.setText(""); // Clear deadline input field
            } else {
                JOptionPane.showMessageDialog(mainPanel, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Task Manager");
            frame.setContentPane(new VA_Tasks().getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
