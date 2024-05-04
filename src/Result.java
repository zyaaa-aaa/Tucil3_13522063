import java.util.*;

public class Result {
    private List<String> path;
    private int visitedNodes;

    public Result(List<String> path, int visitedNodes) {
        this.path = path;
        this.visitedNodes = visitedNodes;
    }
    
    public List<String> getPath() {
        return path;
    }

    public int getVisitedNodes() {
        return visitedNodes;
    }
}
