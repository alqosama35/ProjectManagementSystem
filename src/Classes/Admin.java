package Classes;
import Utils.FileManager;
import java.util.ArrayList;
import java.util.List;
public class Admin {

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

}
