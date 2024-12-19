package Classes;

import java.util.ArrayList;
import java.util.List;
import Enum.Role;
import Enum.VacationStatus;
import Utils.FileManager;

import static Enum.Role.EMP;
import static Enum.Role.TEAM_LEADER;
import static Enum.TaskStatus.COMPLETED;

public class TeamLeader extends User {
    public TeamLeader(String name, String email, String password) {
        super(name, email, Role.EMP, password);
    }

    // Constructor for login
    public TeamLeader(String email, String password) {
        super(email, password);
    }

    public void assignTask(Task task, Employee employee) {
        employee.addAssignedTask(task);
        task.setAssignedTo(employee.getUserId());
    }

    public void addPenalty(int userId, Penalty penalty) {
        FileManager fileManager = new FileManager();
        ArrayList<Employee> users = fileManager.readArrayFromFile("User", Employee[].class);
        for(Employee employee : users) {
            if(employee.getUserId() == userId) {
                employee.addPenalty(penalty);
            }
        }
        fileManager.updateFile(users, "User");
    }

    public void manageVacation(int userId, int requestId, VacationStatus status) {
        FileManager fileManager = new FileManager();
        ArrayList<Employee> users = fileManager.readArrayFromFile("User", Employee[].class);
        for(Employee employee : users) {
            if(employee.getUserId() == userId) {
                List<VacationRequest> requests = employee.getVacationRequests();
                for(VacationRequest request : requests) {
                    if(request.getRequestId() == requestId) {
                        request.setStatus(status);
                    }
                }
            }
        }
        fileManager.updateFile(users, "User");
    }

    public List<Task> getCompletedTasks(Project project) {
        // Logic to view completed tasks for team
        ArrayList<Task> completedTasks = new ArrayList<Task>();
        List<Task> tasks = project.getTasks();
        for (Task task : tasks) {
            if (task.getStatus() == COMPLETED) {
                completedTasks.add(task);
            }
        }

        return completedTasks;

    }

    public void generateEmployeeReport(Employee employee) {
        // Logic to generate report for a specific employee
        FileManager fileManager = new FileManager();
        fileManager.saveToFile(employee.toString(), "EmployeeReport");
    }

    public Employee getEmployeeById(int userId) {
        FileManager fileManager = new FileManager();
        Employee[] users = (Employee[]) fileManager.readFromFile("User", Employee[].class);
        for (Employee user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees()
    {
        FileManager fileManager = new FileManager();
        ArrayList<Employee> employees = fileManager.readArrayFromFile("User", Employee[].class);
        if (employees != null) {
            return employees;
        }
        else {
            return null;
        }
    }

    public Employee getEmployeeByName(String name) {
        FileManager fileManager = new FileManager();
        Employee[] users = (Employee[]) fileManager.readFromFile("User", Employee[].class);
        for (Employee user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", TeamLeader[].class);
        for (User user : users) {
            if (user.getEmail().equals(super.getEmail())
                    && user.getPassword().equals(super.getPassword())
                    && user.getRole() == TEAM_LEADER) {
                // Initialize the object with the found user's details
                this.setUserId(user.getUserId());
                this.setName(user.getName());
                this.setEmail(user.getEmail());
                this.setRole(user.getRole());
                this.setPassword(user.getPassword());
                return true;
            }
        }
        return false;
    }
}