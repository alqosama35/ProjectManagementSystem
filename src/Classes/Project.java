package Classes;

import java.util.List;
import Enum.TaskStatus;

public class Project {

    private String projectId;
    private String projectName;
    private List<Integer> teamMembers;
    private int PMId;
    private List<Task> tasks;
    private double completionPercentage;

    public Project(int projectId, String projectName, List<Integer> teamMembers, Integer PMId, List<Task> tasks, double completionPercentage) {
        this.projectId = String.valueOf(projectId);
        this.projectName = projectName;
        this.teamMembers = teamMembers;
        this.PMId = PMId;
        this.tasks = tasks;
        this.completionPercentage = completionPercentage;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = String.valueOf(projectId);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Integer> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<Integer> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getPMId() {
        return PMId;
    }

    public void setPMId(int PMId) {
        this.PMId = PMId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public double calculateCompletionPercentage() {
        if (tasks == null || tasks.isEmpty()) {
            return 0;
        }
        long completedTasks = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();
        return (double) completedTasks / tasks.size() * 100;
    }

    public void addTask(Task task) {
        if (tasks != null) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        if (tasks != null) {
            tasks.remove(task);
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
