package Test;


import Classes.User;
import Utils.FileManager;

public class TestEmp extends User {
    public TestEmp(int userId, String name, String email, String role, String password) {
        super(userId, name, email, role, password);
    }

    public TestEmp(String email, String password) {
        super(email, password);
    }

    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", TestEmp[].class);
        for (User user : users) {
            if (user.getEmail().equals(super.getEmail()) && user.getPassword().equals(super.getPassword()) && user.getRole().equals("employee")) {
                // Initialize the object with the found user's details
                this.setUserId(user.getUserId());
                this.setName(user.getName());
                this.setEmail(user.getEmail());
                this.setRole(user.getRole());
                this.setPassword(user.getPassword());
                return true;
            }
        }
        return false;
    }
}
