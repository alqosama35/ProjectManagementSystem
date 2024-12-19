package GUI;

import Classes.TeamLeader;

import javax.swing.*;
import java.awt.*;

public class TeamLeaderPage {

    private JButton assignAndViewTasksButton;
    private JButton manageEmpButton;
    private TeamLeader teamLeader;

    public TeamLeaderPage(TeamLeader teamLeader) {
        if (teamLeader == null) {
            throw new IllegalArgumentException("TeamLeader object cannot be null");
        }

        this.teamLeader = teamLeader;

        // Create the buttons
        assignAndViewTasksButton = new JButton("Assign and View Tasks");
        manageEmpButton = new JButton("Manage Employees");

        // Set up button actions
        assignAndViewTasksButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    VA_Tasks assignAndViewTasks = new VA_Tasks(teamLeader);
                    assignAndViewTasks.showGUI();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error opening tasks view: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        manageEmpButton.addActionListener(e -> {
            try {
                if (teamLeader.login()) {
                    SwingUtilities.invokeLater(() -> new ManageEmp(teamLeader));
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed. Please check credentials.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error during login: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel createPanel() {
        // Create a panel for the Team Leader page
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center aligned with spacing

        // Add the buttons to the panel
        panel.add(assignAndViewTasksButton);
        panel.add(manageEmpButton);

        return panel;
    }

    public void showGUI() {
        // Create the frame for Team Leader Page
        JFrame frame = new JFrame("Team Leader Page");

        // Set the content of the frame to the panel created in TeamLeaderPage
        frame.setContentPane(createPanel());

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose resources on close
        frame.pack();
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null); // Center the window on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TeamLeader teamLeader = new TeamLeader("jane.smith@example.com", "password456");
                new TeamLeaderPage(teamLeader).showGUI();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failed to start the application: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
