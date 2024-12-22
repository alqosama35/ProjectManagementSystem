package Classes;
import Utils.FileManager;
import java.util.ArrayList;
import java.util.List;
import Enum.Role;

import static Enum.Role.ADMIN;
import static Enum.Role.EMP;

public class Admin extends User{

    FileManager fileManager=new FileManager();
    // Constructor for new Employee
    public Admin(String name, String email, Role role, String password) {
        super(name, email, role, password);

    }

    // Constructor for login
    public Admin(String email, String password) {
        super(email, password);
    }
//    public void addUser(User user){
//       // ArrayList<User> Users = (ArrayList<User>) fileManager.readFromFile("User", User[].class);
//        ArrayList<User> Users = null;
//
//        // Attempt to read users from the file
//        User[] usersArray = (User[]) fileManager.readFromFile("User", User[].class);
//
//        Users.add(user);
//        fileManager.saveToFile(Users, "User");
//    }


    public void addUser(User user) {
        ArrayList<User> Users = (ArrayList<User>) fileManager.readArrayFromFile("User", User[].class);
        if (Users == null) {
            Users = new ArrayList<>(); // Initialize if null
        }
        Users.add(user);
        fileManager.updateFile(Users, "User");
    }

    public  void updateUser (User user){
        ArrayList<User> Users = (ArrayList<User>) fileManager.readArrayFromFile("User", User[].class);
//        int index = Users.indexOf(user);
//        Users.set(index, user);
//        fileManager.saveToFile(Users, "User");

        int index = -1;
        for (int i = 0; i < Users.size(); i++) {
            if (Users.get(i).getUserId() == user.getUserId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Users.set(index, user); // Update user if found
            fileManager.saveToFile(Users, "User");
        } else {
            System.out.println("User not found for update.");
        }
    }
    public void deleteUser(int userid){
        ArrayList<User> Users = (ArrayList<User>) fileManager.readArrayFromFile("User", User[].class);
        for(User u : Users){
            if(u.getUserId() == userid ){
                Users.remove(u);
                break;
            }

        }
        fileManager.saveToFile(Users, "User");
    }
    public <Arraylist> Object getAllProject(){
        return (ArrayList<Project>) fileManager.readArrayFromFile("Project", Project[].class);

    }
    public <Arraylist> Object getAllUser(){
        return (ArrayList<User>) fileManager.readArrayFromFile("User", User[].class);
    }

    // Login method
    public boolean login() {
        FileManager fileManager = new FileManager();
        User[] users = (User[]) fileManager.readFromFile("User", Employee[].class);
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