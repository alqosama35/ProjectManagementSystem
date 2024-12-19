package GUI;
////
////
////import Classes.Admin;
////import Classes.Penalty;
////import Classes.Project;
////
////import javax.swing.*;
////import javax.swing.table.DefaultTableModel;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.util.ArrayList;
//import Classes.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public static class adminGUI {
//    private JFrame mainFrame;
//    private JPanel mainPanel, projectPanel, userPanel;
//    private JButton projectButton, userButton, addUserButton, updateUserButton, deleteUserButton;
//    private JTable projectTable, userTable;
//    private DefaultTableModel projectTableModel, userTableModel;
//    private Admin admin;
//
//    public adminGUI() {
//        admin = new Admin();
//
//
//        mainFrame = new JFrame("Admin Panel");
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        mainPanel = new JPanel(new GridLayout(1, 2));
//        projectButton = new JButton("All Projects");
//        userButton = new JButton("Manage Users");
//        mainPanel.add(projectButton);
//        mainPanel.add(userButton);
//
//
//        projectPanel = new JPanel(new BorderLayout());
//        projectTableModel = new DefaultTableModel();
//        projectTable = new JTable(projectTableModel);
//        JScrollPane projectScrollPane = new JScrollPane(projectTable);
//        projectPanel.add(projectScrollPane, BorderLayout.CENTER);
//
//
//        userPanel = new JPanel(new BorderLayout());
//        userTableModel = new DefaultTableModel();
//        userTable = new JTable(userTableModel);
//        JScrollPane userScrollPane = new JScrollPane(userTable);
//        userPanel.add(userScrollPane, BorderLayout.CENTER);
//
//
//        projectButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Show Project Panel
//                mainFrame.getContentPane().removeAll();
//                mainFrame.getContentPane().add(projectPanel);
//                mainFrame.pack();
//                mainFrame.revalidate();
//                loadProjects();
//            }
//        });
//
//        userButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Show User Panel
//                mainFrame.getContentPane().removeAll();
//                mainFrame.getContentPane().add(userPanel);
//                mainFrame.pack();
//                mainFrame.revalidate();
//                loadUsers(); // Load user data on panel switch
//            }
//        });
//
//        // Load initial panel
//        mainFrame.getContentPane().add(mainPanel);
//        mainFrame.pack();
//        mainFrame.setVisible(true);
//    }
//
//    public void loadProjects() {
//        // Clear existing data
//        projectTableModel.setRowCount(0);
//
//        // Get projects from Admin
//        ArrayList<Project> projects = (ArrayList<Project>) admin.getAllProject();
//
//        // Set table headers
//        String[] columnNames = {"Project ID", "Project Name", "Team Members", "Manager ID", "Completion %"};
//        projectTableModel.setColumnIdentifiers(columnNames);
//
//        // Add projects to table
//        for (Project project : projects) {
//            Object[] rowData = {project.getProjectId(), project.getProjectName(), project.getTeamMembers(),
//                    project.getPMId(), project.calculateCompletionPercentage()};
//            projectTableModel.addRow(rowData);
//        }
//    }
//
//    public void loadUsers() {
//        private void createUserPanel (){
//            JPanel addUserPanel = new JPanel(new GridLayout(4, 2));
//            addUserPanel.add(new JLabel("Username:"));
//            JTextField usernameField = new JTextField();
//            JTextField EmailField = new JTextField();
//            JTextField PasswordField = new JTextField();
//            JTextField RoleField = new JTextField();
//            addUserPanel.add(usernameField);
//            addUserPanel.add(EmailField);
//            addUserPanel.add(PasswordField);
//            addUserPanel.add(RoleField);
//            JButton addUserButton = new JButton("Add User");
//            addUserPanel.add(addUserButton);
//
//            addUserButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String username = usernameField.getText();
//                    String Email = EmailField.getText();
//                    String Password = PasswordField.getText();
//                    String Role = RoleField.getText();
//                    User newUser = new User() {
//                        @Override
//                        public boolean login() {
//                            return false;
//                        }
//                    };
//                    admin.addUser(newUser);
//
//                    loadUsers();
//                }
//            });
//
//            userPanel.add(addUserPanel, BorderLayout.NORTH);
//        }
//
//        private void createDeleteUserPanel() {
//            JPanel deleteUserPanel = new JPanel(new FlowLayout());
//            JLabel userIdLabel = new JLabel("User ID:");
//            JTextField userIdField = new JTextField(10);
//            JButton deleteUserButton = new JButton("Delete User");
//            deleteUserPanel.add(userIdLabel);
//            deleteUserPanel.add(userIdField);
//            deleteUserPanel.add(deleteUserButton);
//
//            deleteUserButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int userId = Integer.parseInt(userIdField.getText());
//                    admin.deleteUser(userId);
//
//                    loadUsers();
//                }
//            });
//
//            userPanel.add(deleteUserPanel, BorderLayout.SOUTH);
//        }
//    }
//
//    // Placeholder methods for user management functionality (not shown in detail)
//    private void addUser() {
//        // Implement logic to add a new user
//    }
//
//    private void updateUser() {
//        // Implement logic to update an existing user
//    }
//
//    private void deleteUser() {
//        // Implement logic to delete a user
//    }
//
//}
//    public static void main(String[] args) {
//        new adminGUI();
//    }




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

        addUserPanel.add(new JLabel("Password:"));
        JTextField passwordField = new JTextField();
        addUserPanel.add(passwordField);

        addUserPanel.add(new JLabel("Role:"));
        JTextField roleField = new JTextField();
        addUserPanel.add(roleField);

        JButton addUserButton = new JButton("Add User");
        addUserPanel.add(addUserButton);
        userPanel.add(addUserPanel);

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String role = roleField.getText();

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

        updateUserPanel.add(new JLabel("Password:"));
        JTextField updatePasswordField = new JTextField();
        updateUserPanel.add(updatePasswordField);

        updateUserPanel.add(new JLabel("Role:"));
        JTextField updateRoleField = new JTextField();
        updateUserPanel.add(updateRoleField);

        JButton updateUserButton = new JButton("Update User");
        updateUserPanel.add(updateUserButton);
        userPanel.add(updateUserPanel);

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = updateUsernameField.getText();
                String email = updateEmailField.getText();
                String password = updatePasswordField.getText();
                String role = updateRoleField.getText();

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
