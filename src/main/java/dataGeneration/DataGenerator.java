package dataGeneration;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * The DataGenerator class generates random transaction data and stores it in a CSV file.
 * The generated data includes transaction IDs, sender and receiver names, transaction amounts, timestamps, and Unix time.
 */

public class DataGenerator {
    /**
     * The number of records to generate.
     */
    private static final int RECORD_COUNT = 200_000;
    /**
     * The length of the generated sender and receiver names.
     */
    private static final int NAME_LENGTH = 10;
    /**
     * The set of characters used to generate sender and receiver names.
     */
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-_";
    /**
     * The random number generator used to generate data.
     */
    static Random random = new Random();
    /**
     * The main method that generates random transaction data, writes it to a CSV file, and then sorts it using an external merge sort.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {

        try{
            FileWriter writer = new FileWriter("data.csv");

            for (int i = 0; i < RECORD_COUNT; i++) {
                String transactionId = UUID.randomUUID().toString();
                String from=getRandomName();
                String to=getRandomName();
                double amount=getRandomAmount();
                Timestamp timestamp=randomTimeStampGenerator();
                String unixtime=Long.toString(timestamp.getTime() / 1000);
                String[] data={transactionId,from,to,Double.toString(amount),unixtime};

                writer.write(String.join(",",data));
                writer.write("\n");
            }
            writer.flush();
            writer.close();
            ExternalMergeSort externalMergeSort=new ExternalMergeSort();
            externalMergeSort.sort("data.csv","transactions.csv");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Generates a random name consisting of NAME_LENGTH number of characters.
     *
     * @return A string representing a randomly generated name.
     */
    static String getRandomName(){

        StringBuilder name = new StringBuilder();
        for (int j = 0; j < NAME_LENGTH; j++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            name.append(randomChar);
        }
        return name.toString();
    }
    /**
     * Generates a random amount between 1 and 100000
     *
     * @return A double representing a randomly generated amount.
     */
    static double getRandomAmount(){
        double rangeMin=1,rangeMax=100000;
        double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
        DecimalFormat decfor = new DecimalFormat("0.00");
        return Double.parseDouble(decfor.format(randomValue));
    }
    /**
     * Generates a random timestamp between 2012-01-01 00:00:00 and 2012-01-01 05:00:00.
     *
     * @return A Timestamp object representing a randomly generated timestamp.
     */
    static Timestamp randomTimeStampGenerator() {
        long offset = java.sql.Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = java.sql.Timestamp.valueOf("2012-01-01 05:00:00").getTime();
        long diff = end - offset + 1;
        Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
        return rand;
    }
}




