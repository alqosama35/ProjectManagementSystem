package Test;

import Classes.User;

public class TestUser {
    public static void main(String[] args) {
        TestEmp emp = new TestEmp("john.newemail@example.com", "password123");
        if (emp.login()) {
            System.out.println("Welcome " + emp.getName() + "!");
            System.out.println("Login successful!");
        }
        else {
            System.out.println("Login failed!");
        }

    }
}