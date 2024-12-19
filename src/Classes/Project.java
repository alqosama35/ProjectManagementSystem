package Classes;

import java.util.ArrayList;
import java.util.List;
import Utils.*;

public class Project {
    private int projectId;
    private String projectName;
    private List<Integer> tasks;
    private List<User> teamMembers;
    private int PMId;
    private double completionPercentage;

    public Project(int projectId, String projectName, List<Integer> tasks, int PMId, List<User> teamMembers, double completionPercentage) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.tasks = tasks;
        this.PMId = PMId;
        this.teamMembers = teamMembers;
        this.completionPercentage = completionPercentage;
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Task> getTasks() {
        FileManager fileManager = new FileManager();
        Task[] allTasks = (Task[]) fileManager.readFromFile("Task",Task[].class);
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (this.tasks.contains(task.getTaskId())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }

    public List<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getPMId() {
        return PMId;
    }

    public void setPMId(int PMId) {
        this.PMId = PMId;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    // Method to calculate completion percentage
    public double calculateCompletionPercentage() {
        // Implement the logic to calculate completion percentage
        return completionPercentage;
    }
}