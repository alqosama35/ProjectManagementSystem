package Classes;

import Utils.FileManager;
import Enum.Role;

import java.util.ArrayList;
import java.util.List;

import static Enum.Role.PM;

public class ProjectManager extends User {
    private List<Project> managedProjects; // Instance field for managed projects

    // Constructor for new ProjectManager
    public ProjectManager(String name, String email, Role role, String password, List<Project> managedProjects) {
        super(name, email, role, password);
        this.managedProjects = managedProjects != null ? managedProjects : new ArrayList<>();
    }

    // Constructor for login
    public ProjectManager(String email, String password) {
        super(email, password);
        this.managedProjects = new ArrayList<>(); // Initialize with an empty list
    }

    // Generate a team report for all managed projects
    public void generateTeamReport() {
        if (managedProjects == null || managedProjects.isEmpty()) {
            System.out.println("No projects to generate a report for.");
            return;
        }
        System.out.println("Team Report:");
        for (Project project : managedProjects) {
            System.out.println("Project: " + project.getProjectName());
            System.out.println("Team Members: " + project.getTeamMembers());
            System.out.println("Project Manager ID: " + project.getPMId());
            System.out.println("Completion: " + project.calculateCompletionPercentage() + "%");
            System.out.println("-------------------------------------------------");
        }
    }

    // Create a detailed report for a specific project
    public void createProjectReport(Project project) {
        if (project == null) {
            System.out.println("Project not found.");
            return;
        }
        System.out.println("Project Report for " + project.getProjectName() + ":");
        System.out.println("Project ID: " + project.getProjectId());
        System.out.println("Team Members: " + project.getTeamMembers());
        System.out.println("Project Manager ID: " + project.getPMId());
        System.out.println("Tasks:");
        for (Task task : project.getTasks()) {
            System.out.println("Task: " + task.getDescription() + " - Status: " + task.getStatus());
        }
        System.out.println("Completion: " + project.calculateCompletionPercentage() + "%");
    }

    // Login method
    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", User[].class);

        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(super.getEmail())
                        && user.getPassword().equals(super.getPassword())
                        && user.getRole() == PM) {
                    // Initialize this object with the found user's details
                    this.setUserId(user.getUserId());
                    this.setName(user.getName());
                    this.setEmail(user.getEmail());
                    this.setRole(user.getRole());
                    this.setPassword(user.getPassword());

                    // Load managed projects for this Project Manager
                    List<Project> allProjects = fileManager.readArrayFromFile("Project", Project[].class);
                    if (allProjects != null) {
                        this.managedProjects = new ArrayList<>();
                        for (Project project : allProjects) {
                            if (project.getPMId() == this.getUserId()) {
                                this.managedProjects.add(project);
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Getters and Setters
    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(List<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }
}
