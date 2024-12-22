package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.*;
import Classes.ProjectManager;
import Enum.Role;
import Utils.FileManager;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel LoginPanel;
    private JButton loginBtn;
    private JPanel RolePanel;

    public Login() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField1.getText();
                String password = new String(passwordField1.getPassword());
                Role role = null;

                // Determine the selected role
//                if (adminRadioButton.isSelected()) {
//                    role = ADMIN;
//                } else if (teamLeaderRadioButton.isSelected()) {
//                    role = TEAM_LEADER;
//                } else if (PMRadioButton.isSelected()) {
//                    role = PM;
//                } else if (empRadioButton.isSelected()) {
//                    role = EMP;
//                }
                //get the user Role
                FileManager fileManager = new FileManager();
                Employee[] users = (Employee[]) fileManager.readFromFile("User", Employee[].class);
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        role = user.getRole();
                        break;
                    }
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
                        Admin admin = new Admin(email, password);
                        if (admin.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + admin.getName() + "!");
                            openAdminPage(admin);
                        } else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProjectManagerPage teamLeaderPage = new ProjectManagerPage(pm);
                teamLeaderPage.showGUI();
            }
        });
    }

    private void openAdminPage(Admin admin) {
        // Implement the method to open the Admin page
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminGUI teamLeaderPage = new AdminGUI(admin);
                teamLeaderPage.showGUI();
            }
        });

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