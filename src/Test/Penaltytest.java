package Test;

import Classes.Penalty;

public class Penaltytest {
    public static void main(String[] args) {
        // Test Case 1: Default date (current date)
        Penalty penalty1 = new Penalty("Late submission", 100.0);
        System.out.println("----- Test Case 1: Default Date -----");
        System.out.println(penalty1.showPenalty());

        // Test Case 2: Valid custom date
        Penalty penalty2 = new Penalty("Missed deadline", 50.5, "2024-10-01");
        System.out.println("\n----- Test Case 2: Valid Custom Date -----");
        System.out.println(penalty2.showPenalty());

        // Test Case 3: Invalid date format (fallback to current date)
        Penalty penalty3 = new Penalty("Incorrect date input", 75.0, "invalid-date");
        System.out.println("\n----- Test Case 3: Invalid Date Format -----");
        System.out.println(penalty3.showPenalty());

        // Test Case 4: Ensure unique penalty IDs
        Penalty penalty4 = new Penalty("Damaged equipment", 150.0);
        Penalty penalty5 = new Penalty("Unauthorized absence", 200.0);
        System.out.println("\n----- Test Case 4: Unique IDs -----");
        System.out.println("Penalty 4 ID: " + penalty4.getPenaltyId());
        System.out.println("Penalty 5 ID: " + penalty5.getPenaltyId());

        // Test Case 5: Testing setters and getters
        System.out.println("\n----- Test Case 5: Setters and Getters -----");
        penalty1.setDescription("Updated description");
        penalty1.setAmount(120.0);
        penalty1.setDate("2024-09-30");
        System.out.println("Updated Penalty 1: ");
        System.out.println(penalty1.showPenalty());
    }
}
