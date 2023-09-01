import java.io.*;

import solution.*;

public class Main {
    public static void main(String[] args) throws IOException{
        AcceptableNumber acceptableNumber = new AcceptableNumber();
        ConvertFileToJson convertFileToJson = new ConvertFileToJson();
        WordFromFileCounter wordFromFileCounter = new WordFromFileCounter();
        String filePathToReadNumbers = "numbers.txt";
        String filePathToReadWords = "words.txt";
        String filePathToReadUsers = "users.txt";
        String filePathToWriteUsers = "user.json";
        convertFileToJson.convertFileToJson(filePathToReadUsers, filePathToWriteUsers);
        acceptableNumber.numbersReader(filePathToReadNumbers);
        wordFromFileCounter.counter(filePathToReadWords);
    }
}