package DataStructures;
public class Transaction {
	long id;
    String from;
    String to;
    double amount;
    long timestamp;

    public Transaction(long id,String from, String to, double amount, long timestamp) {
    	this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
    }

	public long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public double getAmount() {
		return amount;
	}

	public long getTimestamp() {
		return timestamp;
	}
    
    

}
