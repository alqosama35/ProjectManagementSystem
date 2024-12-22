package Classes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Penalty {
    private static int idCounter = 1; // Static counter for unique IDs
    private int penaltyId;
    private String description;
    private double amount;
    private Date date;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor with default date (current date)
    public Penalty(String description, double amount) {
        this.penaltyId = ++idCounter;  // Increment and assign unique ID
        this.description = description;
        this.amount = amount;
        this.date = new Date(); // Set current date
    }

    // Constructor with custom date (string input)
    public Penalty(String description, double amount, String date) {
        this.penaltyId = ++idCounter;  // Increment and assign unique ID
        this.description = description;
        this.amount = amount;
        try {
            this.date = DATE_FORMAT.parse(date); // Parse and set the date
        } catch (ParseException e) {
            System.out.println("Invalid date format. Setting to today's date.");
            this.date = new Date(); // Set to current date in case of invalid format
        }
    }

    // Getters and Setters
    public int getPenaltyId() {
        return penaltyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return DATE_FORMAT.format(date);
    }

    public void setDate(String date) {
        try {
            this.date = DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Date not updated.");
        }
    }

    public static int getIdCounter() {
        return idCounter;
    }

    // Display penalty details
    public String showPenalty() {
        return  "Penalty number: " + penaltyId + "\n" +
                "Your penalty is: " + description + "\n" +
                "The amount is: $" + amount + "\n" +
                "Penalty's date: " + getDate();
    }
}
