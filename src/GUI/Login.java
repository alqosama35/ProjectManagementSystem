package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                        JOptionPane.showMessageDialog(null, "Employee login logic here.");
                        break;
                    case TEAM_LEADER:
                        JOptionPane.showMessageDialog(null, "Team Leader login logic here.");
                        break;
                    case PM:
                        JOptionPane.showMessageDialog(null, "Project Manager login logic here.");
                        break;
                    case ADMIN:
                        JOptionPane.showMessageDialog(null, "Admin login logic here.");
                        break;
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