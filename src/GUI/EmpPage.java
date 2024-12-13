package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpPage {

    private JButton button1;
    private JButton button2;
    private JButton button3;

    public EmpPage() {
        // Initialize the buttons
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");

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
                JOptionPane.showMessageDialog(null, "Button 2 clicked!");
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

        return panel;
    }

    public static void main(String[] args) {
        // Create the frame for the Employee Page
        JFrame frame = new JFrame("Employee Page");
        EmpPage empPage = new EmpPage();

        // Set the content of the frame to the panel created in EmpPage
        frame.setContentPane(empPage.createPanel());

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 200);
        frame.setVisible(true);
    }
}
