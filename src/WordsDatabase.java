import java.io.*;
import java.util.*;

public class WordsDatabase {
    public WordsDatabase(String filename) throws IOException {
        wordSet = new LinkedHashSet<>();
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null) {
            wordSet.add(line.trim());
        }
        reader.close();
    }

    public boolean inDictionary(String word) {
        return wordSet.contains(word);
    }

    private Set<String> wordSet;
}