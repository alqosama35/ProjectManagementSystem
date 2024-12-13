package Classes;
import java.util.List;

public class TeamLeader extends Employee {
    public TeamLeader(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    public void assignTask(Task task, Employee employee) {
        employee.addTask(task);
        task.assignEmployee(employee);
    }

    public void manageEmployees(List<Employee> employees) {
        // Logic to manage employees
    }

    public void viewCompletedTasks() {
        // Logic to view completed tasks for team
    }

    public void generateEmployeeReport(Employee employee) {
        // Logic to generate report for a specific employee
    }
}
