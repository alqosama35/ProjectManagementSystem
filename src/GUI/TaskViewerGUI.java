package GUI;

import Classes.Employee;
import Classes.Task;
import Enum.TaskStatus;
import Utils.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TaskViewerGUI {
    private JFrame frame;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private FileManager fileManager;

    public TaskViewerGUI(Employee employee) {
        fileManager = new FileManager();

        frame = new JFrame("Task Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columns = {"Task ID", "Description", "Deadline", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        taskTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);

        loadTasks(employee);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton completeButton = new JButton("Mark as Completed");
        JButton refreshButton = new JButton("Refresh");
        JButton closeButton = new JButton("Close");

        completeButton.addActionListener(e -> markTaskAsCompleted(employee));
        refreshButton.addActionListener(e -> refreshTasks(employee));
        closeButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(completeButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void loadTasks(Employee employee) {
        tableModel.setRowCount(0);
        ArrayList<Task> allTasks = fileManager.readArrayFromFile("Task", Task[].class);

        if (allTasks != null) {
            for (Task task : allTasks) {
                if (employee.getUserId() == task.getAssignedTo()) {
                    tableModel.addRow(new Object[]{
                            task.getTaskId(),
                            task.getDescription(),
                            task.getDeadline(),
                            task.getStatus().toString()
                    });
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No tasks found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void markTaskAsCompleted(Employee employee) {
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow != -1) {
            int taskId = (int) tableModel.getValueAt(selectedRow, 0);

            ArrayList<Task> allTasks = fileManager.readArrayFromFile("Task", Task[].class);

            if (allTasks != null) {
                for (Task task : allTasks) {
                    if (task.getTaskId() == taskId) {
                        task.setStatus(TaskStatus.COMPLETED);
                        break;
                    }
                }
                fileManager.updateFile(allTasks, "Task");
                JOptionPane.showMessageDialog(frame, "Task marked as completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshTasks(employee);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to update the task.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshTasks(Employee employee) {
        loadTasks(employee);
    }
}

