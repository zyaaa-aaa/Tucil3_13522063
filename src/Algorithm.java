import java.util.*;

public class Algorithm {
    public Result solveAStar (String start, String end, WordsDatabase dictionary) {
        Map<String, Integer> mapHeuristic = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getFn));
        Map<String, Integer> costMap = new HashMap<>();
        int startH = heuristicValue(start, end);
        int nodesVisited = 0;

        mapHeuristic.put(start, startH);
        priorityQueue.offer(new Node(start, null, startH));
        costMap.put(start, 0);

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentG = costMap.get(currentNode.getWord());

            nodesVisited++;

            if (currentNode.getWord().equals(end)) {
                return new Result(wordPath(currentNode), nodesVisited);
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
        return new Result(Collections.emptyList(), nodesVisited);
    }

    public Result solveGBFS (String start, String end, WordsDatabase dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> heuristicValue(node.getWord(), end)));
        Map<String, Integer> visitedMap = new HashMap<>();
        priorityQueue.offer(new Node(start, null, 0));
        int nodesVisited = 0;

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            nodesVisited++;

            if (currentNode.getWord().equals(end)) {
                return new Result(wordPath(currentNode), nodesVisited);
            }
            if (!visitedMap.containsKey(currentNode.getWord()) || visitedMap.get(currentNode.getWord()) > heuristicValue(currentNode.getWord(), end)) {
                visitedMap.put(currentNode.getWord(), heuristicValue(currentNode.getWord(), end));
                for (String neighbor : neighboursList(currentNode.getWord(), dictionary)) {
                    int neighborHeuristic = heuristicValue(neighbor, end);

                    if (!visitedMap.containsKey(neighbor) || visitedMap.get(neighbor) > neighborHeuristic) {
                        priorityQueue.offer(new Node(neighbor, currentNode, 0));
                    }
                }
            }
        }
        return new Result(Collections.emptyList(), nodesVisited);
    }

    public Result solveUCS(String start, String end, WordsDatabase dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getFn));
        Set<String> visited = new HashSet<>();
        int nodesVisited = 0;

        priorityQueue.add(new Node(start, null, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            nodesVisited++;

            if (currentNode.getWord().equals(end)) {
                return new Result(wordPath(currentNode), nodesVisited);
            }
            if (!visited.contains(currentNode.getWord())) {
                visited.add(currentNode.getWord());
                for (String neighbor : neighboursList(currentNode.getWord(), dictionary)) {
                    if (!visited.contains(neighbor)) {
                        priorityQueue.add(new Node(neighbor, currentNode, currentNode.getFn() + 1));
                    }
                }
            }
        }
        return new Result(Collections.emptyList(), nodesVisited);
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