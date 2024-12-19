package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.Employee;
import Classes.Penalty;

import java.util.ArrayList;

import Utils.FileManager;

public class EmpPage {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public EmpPage(Employee emp) {
        // Initialize the buttons
        button1 = new JButton("Working Hours");
        button2 = new JButton("View Penalties");
        button3 = new JButton("Request Vacation");
        button4 = new JButton("View and Check Tasks");

        // Set up button actions
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Button 1
                JOptionPane.showMessageDialog(null, "Button 1 clicked!");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Button 2
                ArrayList <Penalty> penalties = new ArrayList<>();
                FileManager fileManager = new FileManager();
                ArrayList<Employee> users = fileManager.readArrayFromFile("User", Employee[].class);
                for (Employee employee : users) {
                    if (employee.getUserId() == emp.getUserId()) {
                        penalties = (ArrayList) employee.getPenalties();
                    }
                }
                new PenaltyViewer(penalties);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Button 3
                JOptionPane.showMessageDialog(null, "Button 3 clicked!");
            }
        });
    }

    public JPanel createPanel() {
        // Create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Layout to arrange buttons horizontally

        // Add the buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        return panel;
    }

    public static void main(String[] args) {
        // Create the frame for the Employee Page
        JFrame frame = new JFrame("Employee Page");
        Employee emp = new Employee("john.doe@example.com","hashed_password");
        EmpPage empPage = new EmpPage(emp);

        // Set the content of the frame to the panel created in EmpPage
        frame.setContentPane(empPage.createPanel());

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }
}
