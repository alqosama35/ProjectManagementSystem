package GUI;

import Classes.Project;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Classes.ProjectManager;

public class ProjectManagerPage {

    private JFrame frame;
    private List<Project> projects;
    private DefaultListModel<String> projectListModel;
    private JList<String> projectList;
    private static ProjectManager pm;

    public ProjectManagerPage(ProjectManager pm) {
        this.pm = pm;
        this.projects = pm.getManagedProjects();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Project Manager Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Manage Projects", createProjectManagementPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createProjectManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // List to display project names
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        updateProjectList();

        JScrollPane scrollPane = new JScrollPane(projectList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Two rows and two columns

        JButton addButton = new JButton("Add Project");
        addButton.addActionListener(e -> addProject());

        JButton removeButton = new JButton("Remove Project");
        removeButton.addActionListener(e -> removeProject());

        JButton viewDetailsButton = new JButton("View Project Details");
        viewDetailsButton.addActionListener(e -> viewProjectDetails());

        JButton generateReportButton = new JButton("Generate Team Report");
        generateReportButton.addActionListener(e -> generateTeamReport());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(generateReportButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void addProject() {
        JTextField nameField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Project Name:"));
        panel.add(nameField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add New Project", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Project Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Project newProject = new Project(
                    projects.size() + 1,
                    name,
                    new ArrayList<>(),
                    0,
                    new ArrayList<>(),
                    completionPercentage()
            );
            projects.add(newProject);
            updateProjectList();
        }
    }

    private void removeProject() {
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            projects.remove(selectedIndex);
            updateProjectList();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a project to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewProjectDetails() {
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            Project selectedProject = projects.get(selectedIndex);
            JOptionPane.showMessageDialog(frame,
                    "Project ID: " + selectedProject.getProjectId() +
                            "\nProject Name: " + selectedProject.getProjectName() +
                            "\nCompletion Percentage: " + String.format("%.2f%%", selectedProject.calculateCompletionPercentage()),
                    "Project Details",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a project to view details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProjectList() {
        projectListModel.clear();
        for (Project project : projects) {
            projectListModel.addElement(project.getProjectName());
        }
    }

    private void generateTeamReport() {
        JFrame reportFrame = new JFrame("Team Report");
        reportFrame.setSize(600, 400);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        StringBuilder report = new StringBuilder();
        report.append("---- Team Report ----\n");
        for (Project project : projects) {
            report.append("Project ID: ").append(project.getProjectId()).append("\n");
            report.append("Project: ").append(project.getProjectName()).append("\n");
            report.append("Team Members: ").append(project.getTeamMembers()).append("\n");
            report.append("Completion Percentage: ").append(String.format("%.2f%%", project.calculateCompletionPercentage())).append("\n");
            report.append("-------------------------------\n");
        }
        reportArea.setText(report.toString());

        reportFrame.add(new JScrollPane(reportArea));
        reportFrame.setVisible(true);
    }

    private double completionPercentage() {
        Random random = new Random();
        return 50 + random.nextDouble() * 50; // Random completion percentage between 50% and 100%
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProjectManagerPage(pm));
    }

    public void showGUI() {
        SwingUtilities.invokeLater(() -> new ProjectManagerPage(pm));
    }

    private static List<Project> createDummyProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Project A", List.of(101, 102), 1, new ArrayList<>(), completionPercentageStatic()));
        projects.add(new Project(2, "Project B", List.of(103, 104), 1, new ArrayList<>(), completionPercentageStatic()));
        return projects;
    }

    private static double completionPercentageStatic() {
        Random random = new Random();
        return 50 + random.nextDouble() * 50;
    }
}
