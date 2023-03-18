package util;

import blockchain.Transaction;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A utility class to generate hash values for transactions and Strings.
 * Java program to calculate SHA-512 hash value
 */
public class Encrypt {

    /**
     * Generates an SHA-512 hash for the given transaction.
     *
     * @param transaction the transaction to generate hash for
     * @return the hash value as a string
     */
    public static String encryptThisTransaction(Transaction transaction) {
        try {
            String input = createJsonString(transaction.getId(), transaction.getFrom(), transaction.getTo(), transaction.getAmount(), transaction.getTimestamp());

            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into sign-um representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashText = new StringBuilder(no.toString(16));

            // Add preceding 0s to make it 32 bit
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }

            // return the HashText
            return hashText.toString();
        }


        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a JSON string for the given transaction fields.
     *
     * @param transactionID the transaction ID
     * @param from          the sender of the transaction
     * @param to            the receiver of the transaction
     * @param amount        the amount involved in the transaction
     * @param timestamp     the timestamp of the transaction
     * @return the JSON string for the transaction
     */
    private static String createJsonString(String transactionID, String from, String to, Double amount, long timestamp) {
        return "{"
                + "\"Transaction ID\": \"" + transactionID + "\","
                + "\"From\": \"" + from + "\","
                + "\"To\": \"" + to + "\","
                + "\"Amount\": \"" + amount + "\","
                + "\"TimeStamp\": " + timestamp + "}";

    }

    /**
     * Generates an SHA-256 hash for the given input string.
     *
     * @param input the input string to generate hash for
     * @return the hash value as a string
     */
    public static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
