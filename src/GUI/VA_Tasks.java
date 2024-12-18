package GUI;

import Classes.Employee;
import Classes.Task;
import Classes.TeamLeader;
import Utils.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static Enum.Role.EMP;

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

        // Set up FileManager
        FileManager fileManager = new FileManager();

        // Initialize users and handle potential null values
        Employee[] users = (Employee[]) fileManager.readFromFile("User", Employee[].class);
        if (users != null) {
            for (Employee user : users) {
                if (user.getRole() == EMP) {
                    assignComboBox.addItem(user.getName());
                }
            }
        }

        // Set up the table model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Task", "Assigned To", "Deadline"}, 0);
        taskTable.setModel(tableModel);

        // Layout setup
        mainPanel.setLayout(new BorderLayout());

        // Input panel setup
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskField);
        inputPanel.add(new JLabel("Assign To:"));
        inputPanel.add(assignComboBox);
        inputPanel.add(new JLabel("Deadline (YYYY-MM-DD):"));
        inputPanel.add(deadlineField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Table panel
        mainPanel.add(new JScrollPane(taskTable), BorderLayout.CENTER);

        // Load existing tasks
        ArrayList<Task> tasks = fileManager.readArrayFromFile("Task", Task[].class);
        if (tasks != null) {
            for (Task task : tasks) {
                if (task != null && task.getDescription() != null) {
                    tableModel.addRow(new Object[]{task.getDescription(), task.getAssignedTo(), task.getDeadline()});
                }
            }
        }

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add button action listener
        addButton.addActionListener(e -> {
            String taskDescription = taskField.getText().trim();
            String assignedTo = (String) assignComboBox.getSelectedItem();
            String deadline = deadlineField.getText().trim();
            int assignedToId = -1;

            // Validate input
            if (taskDescription.isEmpty() || deadline.isEmpty() || assignedTo == null) {
                JOptionPane.showMessageDialog(mainPanel, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find the user ID based on the selected name
            if (users != null) {
                for (Employee user : users) {
                    if (user.getName().equals(assignedTo)) {
                        assignedToId = user.getUserId();
                        break;
                    }
                }
            }

            if (assignedToId == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Assigned user not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create and assign the task
            Task task = new Task(taskDescription, assignedToId, deadline);
            TeamLeader teamLeader = new TeamLeader("John Doe", "john.doe@example.com", "password123");

            boolean taskAssigned = false;
            if (users != null) {
                for (Employee employee : users) {
                    if (employee.getUserId() == assignedToId) {
                        teamLeader.assignTask(task, employee);
                        taskAssigned = true;
                        break;
                    }
                }
            }

            if (!taskAssigned) {
                JOptionPane.showMessageDialog(mainPanel, "Failed to assign task to the user.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update Task.json file
            ArrayList<Task> tasksList = fileManager.readArrayFromFile("Task", Task[].class);
            if (tasksList == null) {
                tasksList = new ArrayList<>();
            }
            tasksList.add(task);
            fileManager.updateFile(tasksList, "Task");

            // Update table
            tableModel.addRow(new Object[]{task.getDescription(), assignedTo, deadline});
            taskField.setText("");          // Clear task field
            assignComboBox.setSelectedIndex(0);
            deadlineField.setText("");      // Clear deadline field

            JOptionPane.showMessageDialog(mainPanel, "Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
