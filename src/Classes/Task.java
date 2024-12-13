package Classes;
import Enum.TaskStatus;

public class Task {
    private int taskId;
    private String description;
    private TaskStatus status; // Enum for status of Task
    private Employee assignedEmployee;
    private String deadline;

    public Task(int taskId, String description, String deadline) {
        this.taskId = taskId;
        this.description = description;
        this.deadline = deadline;
        this.status = TaskStatus.PENDING; // Default status
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void assignEmployee(Employee employee) {
        this.assignedEmployee = employee;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
