package solution;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertFileToJson {

    public void convertFileToJson(String filePathToRead, String filePathToWrite) throws IOException {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathToRead))) {
            String headerLine = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                userList.add(new User(name, age));
            }
        } catch (IOException e) {
            System.out.println("A file with this name does not exist in the path " + filePathToRead);
        }
        if (userList.isEmpty()) {
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);

        try (FileWriter writer = new FileWriter(filePathToWrite)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
