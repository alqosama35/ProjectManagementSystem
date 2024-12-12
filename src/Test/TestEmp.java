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

    @Override
    public User login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", TestEmp[].class);
        for (User user : users) {
            if (user.getEmail().equals(super.getEmail()) && user.getPassword().equals(super.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
