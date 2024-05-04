import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                try {
                    WordsDatabase dictionary = new WordsDatabase("dictionary.txt");
                    new WordLadderGUI(dictionary);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Failed to load the dictionary. Error: " + e.getMessage());
                }
            });
    }
}