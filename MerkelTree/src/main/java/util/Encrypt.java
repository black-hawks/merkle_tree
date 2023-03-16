/**

 A utility class to generate hash values for transactions and Strings.
 Java program to calculate SHA-512 hash value
 */

package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import blockchain.Transaction;
//import org.json.simple.JSONValue;

public class Encrypt {

	/**
	 * Generates a SHA-512 hash for the given transaction.
	 *
	 * @param transaction the transaction to generate hash for
	 * @return the hash value as a string
	 */
	public static String encryptThisTransaction(Transaction transaction)
	{
		try {
			String input = createJsonString(transaction.getId(),transaction.getFrom(), transaction.getTo(), transaction.getAmount(), transaction.getTimestamp());

			// getInstance() method is called with algorithm SHA-512
			MessageDigest md = MessageDigest.getInstance("SHA-512");

			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			// return the HashText
			return hashtext;
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
	 * @param from the sender of the transaction
	 * @param to the receiver of the transaction
	 * @param amount the amount involved in the transaction
	 * @param timestamp the timestamp of the transaction
	 * @return the JSON string for the transaction
	 */
	private static String createJsonString(String transactionID, String from, String to, Double amount, long timestamp) {



		// JSONObject jsonObject = new JSONObject();
		// jsonObject.put("Transaction ID", transactionID);
		// jsonObject.put("From", from);
		// jsonObject.put("To", to);
		// jsonObject.put("Amount", amount);
		// jsonObject.put("Timestamp", timestamp);
		// String jsonString = jsonObject.toString();
		// return jsonString;

		String inputStr = "{"
				+ "\"Transaction ID\": \""+ transactionID +"\","
				+ "\"From\": \""+ from+"\","
				+ "\"To\": \""+ to +"\","
				+ "\"Amount\": \""+ amount +"\","
				+ "\"TimeStamp\": "+timestamp+"}";

		return inputStr;

	}

	/**
	 * Generates a SHA-256 hash for the given input string.
	 *
	 * @param input the input string to generate hash for
	 * @return the hash value as a string
	 */
	public static String generateHash(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes());
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
