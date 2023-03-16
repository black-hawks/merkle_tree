
/**
 * Transaction class represents a financial transaction between two parties.
 */
package blockchain;

import java.util.Date;

public class Transaction {
    /**
     * The unique identifier of the transaction.
     */
    private final String transactionId;

    /**
     * The name of the party who is sending the money.
     */
    private final String from;

    /**
     * The name of the party who is receiving the money.
     */
    private final String to;

    /**
     * The amount of money being transferred in the transaction.
     */
    private final double amount;

    /**
     * The timestamp when the transaction was made.
     */
    private final long timestamp;

    /**
     * Creates a new transaction with the given parameters.
     *
     * @param transactionId The unique identifier of the transaction.
     * @param from          The name of the party who is sending the money.
     * @param to            The name of the party who is receiving the money.
     * @param amount        The amount of money being transferred in the transaction.
     * @param timestamp     The timestamp when the transaction was made.
     */
    public Transaction(String transactionId, String from, String to, double amount, long timestamp) {
        this.transactionId = transactionId;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier of the transaction.
     *
     * @return The unique identifier of the transaction.
     */
    public String getId() {
        return transactionId;
    }

    /**
     * Returns the name of the party who is sending the money.
     *
     * @return The name of the party who is sending the money.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the name of the party who is receiving the money.
     *
     * @return The name of the party who is receiving the money.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the amount of money being transferred in the transaction.
     *
     * @return The amount of money being transferred in the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the timestamp when the transaction was made.
     *
     * @return The timestamp when the transaction was made.
     */
    public long getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", timestamp=" + new Date(timestamp) +
                '}';
    }
}
