public class Transaction {
    String from;
    String to;
    double amount;
    String hash;
    long timestamp;

    public Transaction(String from, String to, double amount, String hash, long timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.hash = hash;
        this.timestamp = timestamp;
    }

}
