package GUI;

import Classes.Employee;
import Classes.VacationRequest;
import Utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class VacationRequestGUI {

    private JFrame frame;
    private JTextField startDateField;
    private JTextField endDateField;
    private JButton submitButton;

    public VacationRequestGUI(Employee employee) {
        frame = new JFrame("Vacation Request");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel startDateLabel = new JLabel("Start Date (yyyy-MM-dd):");
        startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("End Date (yyyy-MM-dd):");
        endDateField = new JTextField();

        submitButton = new JButton("Submit Request");
        submitButton.addActionListener(e -> handleSubmit(employee));

        frame.add(startDateLabel);
        frame.add(startDateField);
        frame.add(endDateLabel);
        frame.add(endDateField);
        frame.add(submitButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleSubmit(Employee employee) {
        String startDateInput = startDateField.getText().trim();
        String endDateInput = endDateField.getText().trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            FileManager fileManager = new FileManager();

            Date startDate = dateFormat.parse(startDateInput);
            Date endDate = dateFormat.parse(endDateInput);

            if (startDate.after(endDate)) {
                JOptionPane.showMessageDialog(frame, "Start date cannot be after end date.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            VacationRequest vacation = new VacationRequest(employee, startDate, endDate);
            ArrayList<VacationRequest> vacationRequest = new ArrayList<>();

            vacation.setRequester(employee);
            vacation.setStartDate(startDate);
            vacation.setEndDate(startDate);

            vacationRequest.add(vacation);
            fileManager.saveToFile(vacationRequest, "VacationRequests");
            JOptionPane.showMessageDialog(frame, "Vacation request submitted!\nRequest ID: " + vacation.getRequestId(), "Success", JOptionPane.INFORMATION_MESSAGE);

            frame.dispose();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Please enter valid dates in the format yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}