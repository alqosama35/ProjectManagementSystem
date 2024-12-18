package Test;

import Classes.Employee;
import Classes.User;
import Utils.FileManager;

import javax.swing.*;
import java.awt.*;

public class ViewEmployeePage {
    void viewEmployee() {
        FileManager fileManager = new FileManager();
        Employee[] users = (Employee[]) fileManager.readFromFile("User",Employee[].class);

        // Extract names from the users array
        String[] userNames = new String[users.length];
        for (int i = 0; i < users.length; i++) {
            userNames[i] = users[i].getName(); // Assuming User class has a getName() method
        }

        // Create a JFrame to hold the GUI components
        JFrame frame = new JFrame("View Employees");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a JList to display the user names
        JList<String> userList = new JList<>(userNames);
        JScrollPane scrollPane = new JScrollPane(userList);

        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Call the viewEmployee method to display the GUI
        new ViewEmployeePage().viewEmployee();
    }
}
