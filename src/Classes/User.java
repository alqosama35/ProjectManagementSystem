package Classes;

import Test.TestEmp;
import Utils.FileManager;
import Enum.Role;

public abstract class User {
    private int userId;
    private String name;
    private String email;
    private Role role;
    private String password;

    public User(){}

    public User(int userId, String name, String email, Role role, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public abstract boolean login();

    public void logout() {
        return;
    }
}
