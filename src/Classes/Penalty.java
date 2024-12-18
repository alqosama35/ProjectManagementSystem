package Classes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Penalty {
    private static int idCounter = 0; // Static counter for unique IDs
    private int penaltyId;
    private String description;
    private double amount;
    private LocalDate date;

    // Constructor with default date (current date)
    public Penalty(String description, double amount) {
        this.penaltyId = ++idCounter;  // Increment and assign unique ID
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    // Constructor with custom date (string input)
    public Penalty(String description, double amount, String date) {
        this.penaltyId = ++idCounter;  // Increment and assign unique ID
        this.description = description;
        this.amount = amount;
        try {
            this.date = LocalDate.parse(date); // Parse and set the date
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Setting to today's date.");
            this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    // Display penalty details
    public String showPenalty() {
        return  "Penalty number: " + penaltyId + "\n" +
                "Your penalty is: " + description + "\n" +
                "The amount is: $" + amount + "\n" +
                "Penalty's date: " + date;
    }
}
