import java.util.*;

public class Algorithm {
    public List<String> solveAStar (String start, String end, WordsDatabase dictionary) {
        Map<String, Integer> mapHeuristic = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getFn));
        Map<String, Integer> costMap = new HashMap<>();
        int startH = heuristicValue(start, end);

        mapHeuristic.put(start, startH);
        priorityQueue.offer(new Node(start, null, startH));
        costMap.put(start, 0);

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentG = costMap.get(currentNode.getWord());

            if (currentNode.getWord().equals(end)) {
                return wordPath(currentNode);
            }

            for (String neighbor: neighboursList(currentNode.getWord(), dictionary)) {
                int currentCost = currentG + 1;
                int neighborH = heuristicValue(neighbor, end);
                int fValue = currentCost + neighborH;

                if (!costMap.containsKey(neighbor) || currentCost < costMap.get(neighbor)) {
                    mapHeuristic.put(neighbor, neighborH);
                    priorityQueue.offer(new Node(neighbor, currentNode, fValue));
                    costMap.put(neighbor, currentCost);
                }
            }
        }
        return Collections.emptyList();
    }

    public List<String> solveGBFS (String start, String end, WordsDatabase dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> heuristicValue(node.getWord(), end)));
        Map<String, Integer> visitedMap = new HashMap<>();
        priorityQueue.offer(new Node(start, null, 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            if (current.getWord().equals(end)) {
                return wordPath(current);
            }
            if (!visitedMap.containsKey(current.getWord()) || visitedMap.get(current.getWord()) > heuristicValue(current.getWord(), end)) {
                visitedMap.put(current.getWord(), heuristicValue(current.getWord(), end));
                for (String neighbor : neighboursList(current.getWord(), dictionary)) {
                    int neighborHeuristic = heuristicValue(neighbor, end);

                    if (!visitedMap.containsKey(neighbor) || visitedMap.get(neighbor) > neighborHeuristic) {
                        priorityQueue.offer(new Node(neighbor, current, 0));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public List<String> solveUCS(String start, String end, WordsDatabase dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getFn));
        Set<String> visited = new HashSet<>();

        priorityQueue.add(new Node(start, null, 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();

            if (current.getWord().equals(end)) {
                return wordPath(current);
            }
            if (!visited.contains(current.getWord())) {
                visited.add(current.getWord());
                for (String neighbor : neighboursList(current.getWord(), dictionary)) {
                    if (!visited.contains(neighbor)) {
                        priorityQueue.add(new Node(neighbor, current, current.getFn() + 1));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private int heuristicValue (String currentWord, String endWord) {
        int hValue = 0;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) != endWord.charAt(i)) {
                hValue++;
            }
        }
        return hValue;
    }

    private List<String> neighboursList(String word, WordsDatabase dictionary) {
        List<String> neighbours = new ArrayList<>();
        char[] letters = word.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            char before = letters[i];
            
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != before) {
                    letters[i] = c;
                    String after = new String(letters);
                    if (dictionary.inDictionary(after)) {
                        neighbours.add(after);
                    }
                }
            }
            letters[i] = before;
        }
        return neighbours;
    }

    private List<String> wordPath (Node node) {
        LinkedList<String> path = new LinkedList<>();
        
        while (node != null) {
            path.addFirst(node.getWord());
            node = node.getParent();
        }

        return path;
    }
}