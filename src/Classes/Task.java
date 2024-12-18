package Classes;

import Enum.TaskStatus;

public class Task {
    private int taskId;
    private String description;
    private TaskStatus status; // Assuming TaskStatus is an enum or class
    private Employee assignedTo; // Assuming Employee is a defined class
    private String deadline;

    // Constructor
    public Task(int taskId, String description, TaskStatus status, String deadline) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    // Getters and Setters
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

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // Method to update task status
    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    // Method to assign an employee
    public void assignEmployee(Employee employee) {
        this.assignedTo = employee;
    }

    // Optional: Override toString for better display
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedTo=" + (assignedTo != null ? assignedTo.getName() : "None") +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
