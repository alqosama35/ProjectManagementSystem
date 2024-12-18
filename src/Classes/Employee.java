package Classes;

import Utils.FileManager;
import Enum.Role;

import java.util.List;
import java.util.ArrayList;

import static Enum.Role.*;

public class Employee extends User {
    private List<Task> assignedTasks;
    private List<WorkHours> workHours;
    private List<Penalty> penalties;
    private List<VacationRequest> vecationRequests;

    // Constructor for new Employee
    public Employee(String name, String email, Role role, String password) {
        super(name, email, role, password);
        this.assignedTasks = new ArrayList<>();
        this.workHours = new ArrayList<>();
        this.penalties = new ArrayList<>();
        this.vecationRequests = new ArrayList<>();
    }

    // Constructor for login
    public Employee(String email, String password) {
        super(email, password);
    }

    // Setters
    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public void setWorkHours(List<WorkHours> workHours) {
        this.workHours = workHours;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    public void setVecationRequests(List<VacationRequest> vecationRequests) {
        this.vecationRequests = vecationRequests;
    }

    // Getters
    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public List<WorkHours> getWorkHours() {
        return workHours;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public List<VacationRequest> getVecationRequests() {
        return vecationRequests;
    }

    // Methods to add items to lists
    public void addAssignedTask(Task task) {
        if (assignedTasks == null) {
            assignedTasks = new ArrayList<>();
        }
        assignedTasks.add(task);
    }

    public void addWorkHour(WorkHours workHour) {
        if (workHours == null) {
            workHours = new ArrayList<>();
        }
        workHours.add(workHour);
    }

    public void addPenalty(Penalty penalty) {
        if (penalties == null) {
            penalties = new ArrayList<>();
        }
        penalties.add(penalty);
    }

    public void addVacationRequest(VacationRequest vacationRequest) {
        if (vecationRequests == null) {
            vecationRequests = new ArrayList<>();
        }
        vecationRequests.add(vacationRequest);
    }

    // Login method
    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", Employee[].class);
        for (User user : users) {
            if (user.getEmail().equals(super.getEmail())
                    && user.getPassword().equals(super.getPassword())
                    && user.getRole() == EMP) {
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

    // toString method
    @Override
    public String toString() {
        return "Employee{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", assignedTasks=" + assignedTasks +
                ", workHours=" + workHours +
                ", penalties=" + penalties +
                ", vecationRequests=" + vecationRequests +
                '}';
    }
}
