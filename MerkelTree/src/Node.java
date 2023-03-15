import java.util.List;
public class Node {
    String merkle_root;
    long timestamp;
    Node previous;
    Node next;
    List<Transaction> transactions;

    public Node(String merkle_root, long timestamp, Node previous, Node next, List<Transaction> transactions) {
        this.merkle_root = merkle_root;
        this.timestamp = timestamp;
        this.previous = previous;
        this.next = next;
        this.transactions = transactions;
    }

}
