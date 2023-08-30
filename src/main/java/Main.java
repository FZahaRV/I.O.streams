import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
class AcceptableNumber {
    private List<String> numbers = new ArrayList<String>();
    private final String numbersRegex = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$";
    private Pattern pattern = Pattern.compile(numbersRegex);
    public List<String> getNumbers() {
        return numbers;
    }
    public void numbersReader(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) !=null){
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    numbers.add(matcher.group());
                }
            }
        }catch (IOException e) {
            System.out.println("A file with this name does not exist in the path " + filePath);
        }
        for (String number : getNumbers()) {
            System.out.println(number);
        }
    }
}
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
class ConvertFileToJson {
    public void ConvertFileToJson(String filePathToRead, String filePathToWrite) throws IOException {
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

class WordFromFileCounter {
    public void counter(String filePathToRead) {
        List<String> words = new ArrayList<>();
        Map<String, Integer> countedWords = new HashMap();
        try (Scanner scanner = new Scanner(new File(filePathToRead))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                words.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("A file with this name does not exist at this path " + filePathToRead);
        }

        for (String word : words) {
            countedWords.put(word, countedWords.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> word : countedWords.entrySet()) {
            System.out.println(word.getKey() + " " + word.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        AcceptableNumber acceptableNumber = new AcceptableNumber();
        ConvertFileToJson convertFileToJson = new ConvertFileToJson();
        WordFromFileCounter wordFromFileCounter = new WordFromFileCounter();
        String filePathToReadNumbers = "C:\\Users\\Alienware\\Desktop\\numbers.txt";
        String filePathToReadWords = "C:\\Users\\Alienware\\Desktop\\words.txt";
        String filePathToReadUsers = "C:\\Users\\Alienware\\Desktop\\users.txt";
        String filePathToWriteUsers = "C:\\Users\\Alienware\\Desktop\\user.json";
        convertFileToJson.ConvertFileToJson(filePathToReadUsers, filePathToWriteUsers);
        acceptableNumber.numbersReader(filePathToReadNumbers);
        wordFromFileCounter.counter(filePathToReadWords);
    }
}