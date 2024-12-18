package GUI;
import Classes.FileHandler;
import Classes.WorkHours;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WorkingHours extends JFrame {

    static WorkHours workHours = new WorkHours();
    static ArrayList<WorkHours> workHoursList = new ArrayList<>();

    private JLabel entryTimeLabel;
    private JTextField entryTimeField;
    private JLabel exitTimeLabel;
    private JTextField exitTimeField;
    private JButton updateButton;
    private JButton saveButton;
    private JLabel resultLabel;
    private JTable timeTable;
    private DefaultTableModel tableModel;

    public WorkingHours() {

        setTitle("Working Hours");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        entryTimeLabel = new JLabel("Enter Entry Time (HH:mm):");
        entryTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        entryTimeField = new JTextField(10);
        entryTimeField.setFont(new Font("Arial", Font.PLAIN, 16));

        exitTimeLabel = new JLabel("Enter Exit Time (HH:mm):");
        exitTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        exitTimeField = new JTextField(10);
        exitTimeField.setFont(new Font("Arial", Font.PLAIN, 16));

        resultLabel = new JLabel("Duration: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        updateButton = new JButton("Calculate");
        updateButton.setFont(new Font("Arial", Font.BOLD, 18));

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));


        String[] columnNames = {"Entry Time", "Exit Time", "Duration"};
        tableModel = new DefaultTableModel(columnNames, 0);
        timeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(timeTable);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(entryTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(entryTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(exitTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(exitTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resultLabel, gbc);

        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(updateButton, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gbc); // Add Save button

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc); // Add JTable

        // ActionListener for Calculate button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String entryTimeText = entryTimeField.getText();
                String exitTimeText = exitTimeField.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                try {
                    Date entryTime = sdf.parse(entryTimeText);
                    Date exitTime = sdf.parse(exitTimeText);

                    workHours.setEntryTime(entryTime);
                    workHours.setExitTime(exitTime);

                    long durationInMillis = exitTime.getTime() - entryTime.getTime();
                    if (durationInMillis < 0) {
                        resultLabel.setText("Exit Time cannot be earlier than Entry Time!");
                    } else {
                        long hours = TimeUnit.MILLISECONDS.toHours(durationInMillis);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60;
                        resultLabel.setText("Duration: " + hours + " hours and " + minutes + " minutes");
                    }
                } catch (ParseException ex) {
                    resultLabel.setText("Invalid Time Format! Use HH:mm.");
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String entryTimeText = entryTimeField.getText();
                String exitTimeText = exitTimeField.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                try {
                    Date entryTime = sdf.parse(entryTimeText);
                    Date exitTime = sdf.parse(exitTimeText);

                    workHours.setEntryTime(entryTime);
                    workHours.setExitTime(exitTime);

                    workHoursList.add(workHours);
                    FileHandler.saveToFile(workHoursList, "work_hours.dat");


                    long durationInMillis = exitTime.getTime() - entryTime.getTime();
                    long hours = TimeUnit.MILLISECONDS.toHours(durationInMillis);
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60;

                    Object[] row = {entryTimeText, exitTimeText, hours + " hours and " + minutes + " minutes"};
                    tableModel.addRow(row);

                    JOptionPane.showMessageDialog(null, "Work hours saved successfully!");

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Time Format! Use HH:mm.");
                } catch (IOException ioEx) {
                    JOptionPane.showMessageDialog(null, "Error saving data!");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WorkingHours();
            }
        });
    }
}
