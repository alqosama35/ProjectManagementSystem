package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Enum.Role;
import Enum.TaskStatus;

public class Employee extends User {
    private List<Task> assignedTasks;
    private int workHours;
//    private List<Penalty> penalties;


    public Employee(int userId, String name, String email, String password) {
        super(userId, name, email, Role.valueOf("Employee"), password);
        this.assignedTasks = new ArrayList<>();
//        this.penalties = new ArrayList<>();
        this.workHours = 0;
    }

    // Methods
    public void enterWorkTime(Date entryTime, Date exitTime) {
        // Calculate work hours and update
        long duration = (exitTime.getTime() - entryTime.getTime()) / (1000 * 60 * 60); // in hours
        this.workHours += duration;
    }

//    public void requestVacation(VacationRequest request) {
//        // Logic for requesting vacation
//    }

    public void viewAssignedTasks() {
        for (Task task : assignedTasks) {
            System.out.println(task);
        }
    }

    public void markTaskComplete(Task task) {
        if (assignedTasks.contains(task)) {
            task.updateStatus(TaskStatus.COMPLETED);
        }
    }

//    public void viewPenalties() {
//        for (Penalty penalty : penalties) {
//            System.out.println(penalty);
//        }
//    }

    public void addTask(Task task) {
        assignedTasks.add(task);
    }

    @Override
    public boolean login() {
        return false;
    }
}
