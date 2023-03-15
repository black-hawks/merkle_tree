package Util;
// Java program to calculate SHA-512 hash value

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import DataStructures.Transaction;
//import org.json.simple.JSONValue;  

public class Encrypt {

	

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

	private static String createJsonString(long transactionID, String from, String to, Double amount, long timestamp) {

        //donot remove this code

        // Map obj=new HashMap();    
        // obj.put("from",from);    
        // obj.put("to", to);    
        // obj.put("amount",amount);   
        // String inputString = JSONValue.toJSONString(obj);  
        // System.out.print(inputString); 
        // return inputString;
        
        //As of now I am creating a JSON String Manually
        //Later I will change it using JSON Class.
        String inputStr = "{" 
        + "\"Transaction ID\": \""+ to +"\","
        + "\"From\": \""+ from+"\","
        + "\"To\": \""+ to +"\"," 
        + "\"Amount\": \""+ amount +"\","                
        + "\"TimeStamp\": "+timestamp+"}";

        System.out.println(inputStr);
        return inputStr;

    }

}
