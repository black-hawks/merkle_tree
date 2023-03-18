/**
 * FlightReader class reads flight information from a CSV file and returns flight data in Flight objects.
 * <p>
 * The flight data includes flight departure time, flight number, and flight durations.
 */
package blockchainMerkleTree.miner;

import blockchainMerkleTree.blockchain.Transaction;
import blockchainMerkleTree.util.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader extends CSVReader {
    private final Duration period;

    /**
     * Constructor for FlightReader class.
     *
     * @param filepath the file path of the CSV file to be read
     * @throws FileNotFoundException if the CSV file is not found
     */
    public TransactionReader(String filepath, Duration period) throws FileNotFoundException {
        super(filepath);
        this.period = period;
    }


    /**
     * Gets the departure date of the flight from the CSV file.
     *
     * @return the departure date of the flight in milliseconds since the epoch
     * @throws IOException    if an I/O error occurs
     * @throws ParseException if the date format is not valid
     */
    private long getTime() throws IOException, ParseException {
        br.mark(100);
        String[] line = this.readLine();
        if (line == null) {
            return -1;
        }
        this.br.reset();
        return Long.parseLong(line[4]);
    }

    /**
     * Gets the flight data from the CSV file and creates a Flight object.
     *
     * @return a Flight object containing the flight data
     * @throws IOException    if an I/O error occurs
     * @throws ParseException if the date or duration format is not valid
     */
    private Transaction getTransaction() throws IOException, ParseException {
        String[] line = this.readLine();
        if (line == null) {
            return null;
        }
        return new Transaction(
                line[0],
                line[1],
                line[2],
                Double.parseDouble(line[3]),
                Long.parseLong(line[4])
        );
    }

    public List<Transaction> getTransactions() {
        try {
            long startTime = getTime();
            if (startTime == -1) {
                return null;
            }
            long endTime = startTime + (period.getSeconds() * 1000);
            List<Transaction> transactions = new ArrayList<>();
            while (getTime() != -1 && getTime() < endTime) {
                transactions.add(getTransaction());
            }
            return transactions;
        } catch (IOException | ParseException e) {
            System.out.println(e);
            return null;
        }
    }
}
