package Classes;
import Utils.FileManager;
import java.util.ArrayList;
import java.util.List;
import Enum.Role;

import static Enum.Role.ADMIN;
import static Enum.Role.EMP;

public class Admin extends User {
    // Constructor for new Admin
    public Admin(String name, String email, Role role, String password) {
        super(name, email, role, password);

    }

    // Constructor for login
    public Admin(String email, String password) {
        super(email, password);
    }

    FileManager fileManager=new FileManager();

    public void addUser(User user){
        ArrayList<User> Users = (ArrayList<User>) fileManager.readFromFile("users", User[].class);
        Users.add(user);
        fileManager.saveToFile(Users, "users");
    }

    public void updateUser(User user){
        ArrayList<User> Users = (ArrayList<User>) fileManager.readFromFile("users", User[].class);
        int index = Users.indexOf(user);
        Users.set(index, user);
        fileManager.saveToFile(Users, "users");
    }
    public void deleteUser(int userid){
        ArrayList<User> Users = (ArrayList<User>) fileManager.readFromFile("users", User[].class);
        for(User u : Users){
            if(u.getUserId() == userid ){
                Users.remove(u);
                break;
            }

        }
        fileManager.saveToFile(Users, "users");
    }
    public <Arraylist> Object getAllProject(){
        return (ArrayList<Project>) fileManager.readFromFile("projects", ArrayList.class);

    }
    public <Arraylist> Object getAllUser(){
        return (ArrayList<User>) fileManager.readFromFile("users", ArrayList.class);
    }

    // Login method
    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", Admin[].class);
        for (User user : users) {
            if (user.getEmail().equals(super.getEmail())
                    && user.getPassword().equals(super.getPassword())
                    && user.getRole() == ADMIN) {
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
