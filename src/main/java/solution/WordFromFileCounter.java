package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordFromFileCounter {
    public static WordFromFileCounter wordFromFileCounter;

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
