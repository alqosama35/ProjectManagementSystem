package GUI;



import Classes.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI {
    private JFrame mainFrame;
    private JPanel mainPanel, projectPanel, userPanel;
    private JButton projectButton, userButton;
    private JTable projectTable;
    private DefaultTableModel projectTableModel;
    private Admin admin;

    public AdminGUI() {
        admin = new Admin();

        mainFrame = new JFrame("Admin Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridLayout(1, 2));
        projectButton = new JButton("All Projects");
        userButton = new JButton("Manage Users");
        mainPanel.add(projectButton);
        mainPanel.add(userButton);

        initializeProjectPanel();
        initializeUserPanel();

        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(projectPanel);
                loadProjects();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(userPanel);
            }
        });

        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
    }

    private void switchToPanel(JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(panel);
        mainFrame.repaint();
        mainFrame.revalidate();
    }

    private void initializeProjectPanel() {
        projectPanel = new JPanel(new BorderLayout());
        projectTableModel = new DefaultTableModel(new String[]{"Project ID", "Project Name", "Team Members", "Manager ID", "Completion %"}, 0);
        projectTable = new JTable(projectTableModel);
        JScrollPane projectScrollPane = new JScrollPane(projectTable);
        projectPanel.add(projectScrollPane, BorderLayout.CENTER);
    }

    private void initializeUserPanel() {
        userPanel = new JPanel(new GridLayout(3, 1));

        // Add User Section
        JPanel addUserPanel = new JPanel(new GridLayout(5, 2));
        addUserPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        addUserPanel.add(usernameField);

        addUserPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        addUserPanel.add(emailField);

        addUserPanel.add(new JLabel("Role:"));
        JTextField roleField = new JTextField();
        addUserPanel.add(roleField);

        addUserPanel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField();
        addUserPanel.add(passwordField);



        JButton addUserButton = new JButton("Add User");
        addUserPanel.add(addUserButton);
        userPanel.add(addUserPanel);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String role = roleField.getText();
                String password = passwordField.getText();


                User newUser = new User() {
                    @Override
                    public boolean login() {
                        return false;
                    }
                };
                admin.addUser(newUser);
                JOptionPane.showMessageDialog(mainFrame, "User added successfully.");
            }
        });

        // Update User Section
        JPanel updateUserPanel = new JPanel(new GridLayout(5, 2));




        updateUserPanel.add(new JLabel("Username:"));
        JTextField updateUsernameField = new JTextField();
        updateUserPanel.add(updateUsernameField);
        updateUserPanel.add(new JLabel("Email:"));
        JTextField updateEmailField = new JTextField();
        updateUserPanel.add(updateEmailField);

        updateUserPanel.add(new JLabel("Role:"));
        JTextField updateRoleField = new JTextField();
        updateUserPanel.add(updateRoleField);

        updateUserPanel.add(new JLabel("Password:"));
        JTextField updatePasswordField = new JTextField();
        updateUserPanel.add(updatePasswordField);



        JButton updateUserButton = new JButton("Update User");
        updateUserPanel.add(updateUserButton);
        userPanel.add(updateUserPanel);

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = updateUsernameField.getText();
                String email = updateEmailField.getText();
                String role = updateRoleField.getText();
                String password = updatePasswordField.getText();


                User updatedUser = new User() {
                    @Override
                    public boolean login() {
                        return false;
                    }
                };
                admin.updateUser(updatedUser);
                JOptionPane.showMessageDialog(mainFrame, "User updated successfully.");
            }
        });

        // Delete User Section
        JPanel deleteUserPanel = new JPanel(new FlowLayout());
        deleteUserPanel.add(new JLabel("User ID:"));
        JTextField deleteUserIdField = new JTextField(10);
        deleteUserPanel.add(deleteUserIdField);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserPanel.add(deleteUserButton);
        userPanel.add(deleteUserPanel);

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = Integer.parseInt(deleteUserIdField.getText());
                admin.deleteUser(userId);
                JOptionPane.showMessageDialog(mainFrame, "User deleted successfully.");
            }
        });
    }

    public void loadProjects() {
        projectTableModel.setRowCount(0);
        ArrayList<Project> projects = (ArrayList<Project>) admin.getAllProject();
        if (projects != null) {
            for (Project project : projects) {
                projectTableModel.addRow(new Object[]{
                        project.getProjectId(),
                        project.getProjectName(),
                        project.getTeamMembers(),
                        project.getPMId(),
                        project.calculateCompletionPercentage()
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminGUI());
    }
}