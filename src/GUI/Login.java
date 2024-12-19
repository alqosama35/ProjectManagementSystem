package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Employee;
import Classes.TeamLeader;
import Classes.ProjectManager;
import Enum.Role;

import static Enum.Role.*;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel LoginPanel;
    private JButton loginBtn;
    private JRadioButton adminRadioButton;
    private JRadioButton teamLeaderRadioButton;
    private JRadioButton PMRadioButton;
    private JRadioButton empRadioButton;
    private JPanel Role;
    private JPanel RolePanel;

    public Login() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField1.getText();
                String password = new String(passwordField1.getPassword());
                Role role = null;

                // Determine the selected role
                if (adminRadioButton.isSelected()) {
                    role = ADMIN;
                } else if (teamLeaderRadioButton.isSelected()) {
                    role = TEAM_LEADER;
                } else if (PMRadioButton.isSelected()) {
                    role = PM;
                } else if (empRadioButton.isSelected()) {
                    role = EMP;
                }

                if (role == null) {
                    JOptionPane.showMessageDialog(null, "Please select a role!");
                    return;
                }

                // Perform login logic based on the role
                switch (role) {
                    case EMP:
                        Employee emp = new Employee(email, password);
                        if (emp.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + emp.getName() + "!");
                            openEmpPage(emp);
                        } else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
                        break;
                    case TEAM_LEADER:
                        TeamLeader teamLeader = new TeamLeader(email, password);
                        if (teamLeader.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + teamLeader.getName() + "!");
                            openTeamLeaderPage(teamLeader);
                        } else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
                        break;
                    case PM:
                        ProjectManager pm = new ProjectManager(email, password);
                        if (pm.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + pm.getName() + "!");
                            openPMPage(pm);
                        } else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
                        break;
                    case ADMIN:
                        JOptionPane.showMessageDialog(null, "Login successful");
                        openAdminPage();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Unknown role.");
                }
            }
        });
    }

    private void openEmpPage(Employee emp) {
        JFrame frame = new JFrame("Employee Page");
        EmpPage empPage = new EmpPage(emp);
        frame.setContentPane(empPage.createPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }

    private void openTeamLeaderPage(TeamLeader teamLeader) {
        // Implement the method to open the Team Leader page
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TeamLeaderPage teamLeaderPage = new TeamLeaderPage(teamLeader);
                teamLeaderPage.showGUI();
            }
        });
    }

    private void openPMPage(ProjectManager pm) {
        // Implement the method to open the Project Manager page
    }

    private void openAdminPage() {
        // Implement the method to open the Admin page
    }

    public void showLoginDialog() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 200);
    }
}