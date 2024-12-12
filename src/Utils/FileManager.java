package Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//temp code for testing
interface FileHandler {
    void saveToFile(Object data, String filename);
    Object readFromFile(String filename, Class clazz);
    void updateFile(Object data, String filename);
    void deleteFromFile(String filename);
}

public class FileManager implements FileHandler {
    private static final String DATA_DIR = "data/";

    public void saveToFile(Object data, String filename) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(data);

            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileWriter writer = new FileWriter(DATA_DIR + filename + ".json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public Object readFromFile(String filename, Class clazz) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATA_DIR + filename + ".json");
            Object obj = gson.fromJson(reader, clazz);
            reader.close();
            return obj;
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
            return null;
        }
    }

    public <T> ArrayList<T> readArrayFromFile(String filename, Class<T[]> clazz) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(DATA_DIR + filename + ".json");
            T[] array = gson.fromJson(reader, clazz);
            reader.close();

            return new ArrayList<>(Arrays.asList(array));
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
            return null;
        }
    }

    public void updateFile(Object data, String filename) {
        deleteFromFile(filename);
        saveToFile(data, filename);
    }

    public void deleteFromFile(String filename) {
        File file = new File(DATA_DIR + filename + ".json");
        file.delete();
    }
}