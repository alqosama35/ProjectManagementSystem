package Test;

import Classes.User;

public class TestUser {
    public static void main(String[] args) {
        Employee emp = new Employee("john.newemail@example.com", "password123");
        User loggedInUser = emp.login();
        if (loggedInUser != null) {
            System.out.println("Logged in user: " + loggedInUser.getName());
        } else {
            System.out.println("Login failed.");
        }
    }
}