package Classes;

import Enum.TaskStatus;

import static Enum.TaskStatus.PENDING;

public class Task {
    private static int idCounter = 1; // Static counter for taskId
    private int taskId;
    private String description;
    private TaskStatus status; // Assuming TaskStatus is an enum or class
    private int assignedTo; // Assuming Employee is a defined class
    private String deadline;


    // Constructor
    public Task(String description, int assignedTo, String deadline) {
        this.taskId = ++idCounter; // Increment the counter and assign to taskId
        this.description = description;
        this.status = PENDING;
        this.deadline = deadline;
        this.assignedTo = assignedTo; // Assign the employee ID to assignedTo
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
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

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
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


    // Optional: Override toString for better display
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedTo=" + (assignedTo != -1 ? assignedTo : "None") +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}