package Classes;

import java.time.LocalDate;

public class Penalty {
    private static int idCounter = 0;
    private int penaltyId;
    private String description;
    private double amount;
    private LocalDate date = LocalDate.now();

    public Penalty(String description, double amount){
        this.penaltyId = ++idCounter;
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public Penalty(String description, double amount, String date){
        this.penaltyId = ++idCounter;
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.parse(date);
    }

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

    public String showPenalty() {
        return  "Penalty number: " + penaltyId + "\n" +
                "Your penalty is: " + description + "\n" +
                "The amount is: " + amount + "\n" +
                "Penalty's date: " + date;
    }
}
