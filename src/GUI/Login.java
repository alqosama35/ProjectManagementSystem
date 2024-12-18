package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Employee;
import Classes.TeamLeader;
import Enum.Role; // Import the Role enum

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


                        Employee emp = new Employee("john.newemail@example.com","password123");
                        if(emp.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + emp.getName() + "!");
                            JOptionPane.showMessageDialog(null, "Login successful!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
                        break;
                    case TEAM_LEADER:
                        TeamLeader user = new TeamLeader("john.newemail@example.com","password123");
                        if(user.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + user.getName() + "!");
                            JOptionPane.showMessageDialog(null, "Login successful!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        }
                        break;
                    case PM:
                        Classes.ProjectManager pm = new Classes.ProjectManager("john.newemail@example.com","password123");
                        if(pm.login()) {
                            JOptionPane.showMessageDialog(null, "Welcome " + pm.getName() + "!");
                            JOptionPane.showMessageDialog(null, "Login successful!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Login failed!");
                        };
                        break;
                    case ADMIN:
                        JOptionPane.showMessageDialog(null, "login successful");
                    default:
                        JOptionPane.showMessageDialog(null, "Unknown role.");
                }
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