public class Node {
    private String word;
    private Node parent;
    private int Fn;

    public Node(String word, Node parent, int Fn) {
        this.word = word;
        this.parent = parent;
        this.Fn = Fn;
    }

    public String getWord() {
        return word;
    }

    public Node getParent() {
        return parent;
    }

    public int getFn() {
        return Fn;
    }
}