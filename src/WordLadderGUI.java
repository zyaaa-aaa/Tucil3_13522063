import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordLadderGUI extends JFrame {
    private JTextField startWordField;
    private JTextField endWordField;
    private JComboBox<String> algorithmDropdown;
    private JTextArea outputArea;

    public WordLadderGUI(WordsDatabase dictionary) {
        setTitle("Word Ladder Game Solver");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fieldsPanel.add(new JLabel("Start Word:"));
        startWordField = new JTextField();
        fieldsPanel.add(startWordField);

        fieldsPanel.add(new JLabel("End Word:"));
        endWordField = new JTextField();
        fieldsPanel.add(endWordField);

        fieldsPanel.add(new JLabel("Algorithm:"));
        algorithmDropdown = new JComboBox<>(new String[]{"Uniform Cost Search (UCS)", "Greedy Best First Search (GBFS)", "A* Search"});
        fieldsPanel.add(algorithmDropdown);

        inputPanel.add(fieldsPanel);

        // Tombol Start
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startWord = startWordField.getText().trim();
                String endWord = endWordField.getText().trim();

                if (startWord.isEmpty() || endWord.isEmpty()) {
                    outputArea.setText("Please provide both Start and End words.");
                    return;
                }

                if (!isValidWord(startWord) || !isValidWord(endWord)) {
                    outputArea.setText("Start and End words should only contain alphabets.");
                    return;
                }

                if (startWord.length() != endWord.length()) {
                    outputArea.setText("Start and End words must have the same length.");
                    return;
                }

                int algorithmChoice = algorithmDropdown.getSelectedIndex() + 1;

                String result = run(startWord, endWord, algorithmChoice);
                outputArea.setText(result);
            }
        });
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(startButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea(10, 30);  // Adjust initial rows and columns as necessary
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private String run(String start, String end, int algorithmChoice) {
        try {
            WordsDatabase dictionary = new WordsDatabase("dictionary.txt");
    
            start = start.toUpperCase();
            end = end.toUpperCase();
    
            if (!dictionary.inDictionary(start) || !dictionary.inDictionary(end)) {
                return "Words not found in dictionary.";
            }
    
            long startTime = System.currentTimeMillis();
            List<String> path;
            Algorithm algo = new Algorithm();
            switch (algorithmChoice) {
                case 1:
                    path = algo.solveUCS(start, end, dictionary);
                    break;
                case 2:
                    path = algo.solveGBFS(start, end, dictionary);
                    break;
                case 3:
                    path = algo.solveAStar(start, end, dictionary);
                    break;
                default:
                    return "Invalid";
            }
            long endTime = System.currentTimeMillis();
    
            if (path == null || path.isEmpty()) {
                return "No path found between " + start + " and " + end;
            }

            for (int i = 0; i < path.size(); i++) {
                path.set(i, capitalizeWord(path.get(i)));
            }    
    
            return "Path: " + String.join(" -> ", path) + "\n" + "Steps: " + (path.size() - 1) + "\n" + "Execution Time: " + (endTime - startTime) + " ms";
        } catch (IOException e) {
            return "Failed to load the dictionary. Error: " + e.getMessage();
        }
    }    

    private String capitalizeWord(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    private boolean isValidWord(String word) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }
}