package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamLeaderPage {

    private JButton assignAndViewTasksButton;
    private JButton manageEmpButton;

    public TeamLeaderPage() {
        // Create the buttons
        assignAndViewTasksButton = new JButton("Assign and View Tasks");
        manageEmpButton = new JButton("Manage Employees");

        // Set up button actions
        assignAndViewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Assign and View Tasks button
                JOptionPane.showMessageDialog(null, "Assign and View Tasks functionality is under development.");
            }
        });

        manageEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Manage Employees button
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
//                        ManageEmp manageEmp = new ManageEmp();
//                        manageEmp.showGUI();
                    }
                });
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

    public static void main(String[] args) {
        // Create the frame for Team Leader Page
        JFrame frame = new JFrame("Team Leader Page");
        TeamLeaderPage teamLeaderPage = new TeamLeaderPage();

        // Set the content of the frame to the panel created in TeamLeaderPage
        frame.setContentPane(teamLeaderPage.createPanel());

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }
}
