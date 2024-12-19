package GUI;

import Classes.TeamLeader;

import javax.swing.*;
import java.awt.*;

public class TeamLeaderPage {

    private JButton assignAndViewTasksButton;
    private JButton manageEmpButton;
    private TeamLeader teamLeader;

    public TeamLeaderPage(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;

        // Create the buttons
        assignAndViewTasksButton = new JButton("Assign and View Tasks");
        manageEmpButton = new JButton("Manage Employees");

        // Set up button actions
        assignAndViewTasksButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                VA_Tasks assignAndViewTasks = new VA_Tasks();
                assignAndViewTasks.showGUI();
            });
        });

        manageEmpButton.addActionListener(e -> {
            if (teamLeader.login()) {
                SwingUtilities.invokeLater(() -> new ManageEmp(teamLeader));
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please check credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel createPanel() {
        // Create a panel for the Team Leader page
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Simple flow layout for buttons

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TeamLeader teamLeader = new TeamLeader("jane.smith@example.com", "password456");
        SwingUtilities.invokeLater(() -> new TeamLeaderPage(teamLeader).showGUI());
    }
}
