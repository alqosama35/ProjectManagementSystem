package Classes;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void saveToFile(Object data, String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(data);
        oos.close();
    }

    public static <T> T readFromFile(String filename, Class<T> clazz) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        T object = clazz.cast(ois.readObject());
        ois.close();
        return object;
    }

    public static <T> ArrayList<T> readArrayFromFile(String filename, Class<T> clazz) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        ArrayList<T> list = (ArrayList<T>) ois.readObject();
        ois.close();
        return list;
    }

    public static void updateFile(Object data, String filename) throws IOException {
        deleteFromFile(filename);
        saveToFile(data, filename);
    }

    public static void deleteFromFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }
}


