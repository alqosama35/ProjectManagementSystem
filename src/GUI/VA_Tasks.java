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
    private TeamLeader teamLeader;

    public VA_Tasks(TeamLeader teamLeader) {
        if (teamLeader == null) {
            throw new IllegalArgumentException("TeamLeader object cannot be null");
        }
        this.teamLeader = teamLeader;

        // Initialize components
        mainPanel = new JPanel(new BorderLayout());
        taskTable = new JTable();
        taskField = new JTextField();
        assignComboBox = new JComboBox<>();
        deadlineField = new JTextField();
        addButton = new JButton("Add Task");

        // Set up FileManager
        FileManager fileManager = new FileManager();

        // Load employees and populate combo box
        Employee[] users = (Employee[]) fileManager.readFromFile("User", Employee[].class);
        if (users != null) {
            for (Employee user : users) {
                if (user.getRole() == EMP) {
                    assignComboBox.addItem(user.getName());
                }
            }
        }

        // Set up the table model
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Task", "Assigned To", "Status", "Deadline"}, 0);
        taskTable.setModel(tableModel);

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
                    String assignedToName = "Unknown";
                    if (users != null) {
                        for (Employee user : users) {
                            if (user.getUserId() == task.getAssignedTo()) {
                                assignedToName = user.getName();
                                break;
                            }
                        }
                    }
                    tableModel.addRow(new Object[]{task.getDescription(), assignedToName, task.getStatus(), task.getDeadline()});
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

            // Validate input
            if (taskDescription.isEmpty() || deadline.isEmpty() || assignedTo == null) {
                JOptionPane.showMessageDialog(mainPanel, "All fields must be filled out!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int assignedToId = -1;
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
            teamLeader.assignTask(task, teamLeader.getEmployeeById(assignedToId)); // Null can be replaced with actual logic for assigning

            // Update Task.json file
            ArrayList<Task> tasksList = fileManager.readArrayFromFile("Task", Task[].class);
            if (tasksList == null) {
                tasksList = new ArrayList<>();
            }
            tasksList.add(task);
            fileManager.updateFile(tasksList, "Task");

            // Update table
            tableModel.addRow(new Object[]{task.getDescription(), assignedTo, "PENDING", deadline});
            taskField.setText("");
            assignComboBox.setSelectedIndex(0);
            deadlineField.setText("");

            JOptionPane.showMessageDialog(mainPanel, "Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Task Manager");
            frame.setContentPane(this.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TeamLeader dummyLeader = new TeamLeader("Dummy", "dummy@example.com", "password");
            new VA_Tasks(dummyLeader).showGUI();
        });
    }
}
