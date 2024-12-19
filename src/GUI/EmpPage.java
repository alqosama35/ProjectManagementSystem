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

        button1 = new JButton("Working Hours");
        button2 = new JButton("View Penalties");
        button3 = new JButton("Request Vacation");
        button4 = new JButton("View and Check Tasks");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WorkingHours();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList <Penalty> penalties = new ArrayList<>();
                FileManager fileManager = new FileManager();
                ArrayList<Employee> users = fileManager.readArrayFromFile("User", Employee[].class);
                for (Employee employee : users) {
                    if (employee.getUserId() == emp.getUserId()) {
                        penalties = (ArrayList)employee.getPenalties();
                    }
                }
                new PenaltyViewer(penalties);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Button 3
                new VacationRequestGUI(emp);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for Button 4
                new TaskViewerGUI(emp);
            }
        });
    }

    public JPanel createPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // Layout to arrange buttons horizontally

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Page");
        Employee emp = new Employee("john.doe@example.com","hashed_password");
        EmpPage empPage = new EmpPage(emp);

        frame.setContentPane(empPage.createPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }
}