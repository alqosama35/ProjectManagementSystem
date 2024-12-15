package Classes;
import Enum.TaskStatus;

public class Task {
    private static int taskCounter = 0;
    private int taskId;
    private String descripiton;
    private TaskStatus status;
    private Employee assigendTo;
    private String deadline;


    public Task(int taskId, String descripiton, Employee assigendTo, String deadline) {
        this.taskId = ++taskCounter;
        this.descripiton = descripiton;
        this.status = TaskStatus.PENDING;
        this.assigendTo = assigendTo;
        this.deadline = deadline;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Employee getAssigendTo() {
        return assigendTo;
    }

    public void setAssigendTo(Employee assigendTo) {
        this.assigendTo = assigendTo;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void updateStatus(TaskStatus status) {
        this.status = status;
    }

    public void assignEmployee(Employee employee){
        this.assigendTo = employee;
    }
}