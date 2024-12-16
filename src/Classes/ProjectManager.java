package Classes;

import java.util.List;

public class ProjectManager {

    private static List<Project> managedProjects;

    public ProjectManager(List<Project> managedProjects) {
        ProjectManager.managedProjects = managedProjects;
    }

    public static void generateTeamReport() {
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

    public static void createProjectReport(Project project) {
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
}
