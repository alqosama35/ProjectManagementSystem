package Classes;

import java.util.List;
import Enum.Role;

import static Enum.Role.EMP;

public class TeamLeader extends User {
    public TeamLeader(String name, String email, String password) {
        super(name, email, Role.EMP, password);
    }

    public void assignTask(Task task, Employee employee) {
        employee.addAssignedTask(task);
        task.setAssignedTo(employee.getUserId());
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


    @Override
    public boolean login() {
        return false;
    }
}