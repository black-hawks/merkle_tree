package DataStructures;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import Util.Encrypt;

public class MerkelTree {
    private List<Transaction> transactions;
    private String root;

    public MerkelTree(List<Transaction> transactions) {
        this.transactions = transactions;
        root = buildTree(transactions);
    }

    public String getRoot() {
        return root;
    }

    private String buildTree(List<Transaction> transactions) {
    	
        List<String> currentLevel = getHashListForTransaction(transactions);
        List<String> nextLevel = new ArrayList<>();

        while (currentLevel.size() > 1) {
            for (int i = 0; i < currentLevel.size(); i += 2) {
                String left = currentLevel.get(i);
                String right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : "";
                String hash = hash(left + right);
                nextLevel.add(hash);
            }
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }

        return currentLevel.get(0);
    }

    private List<String> getHashListForTransaction(List<Transaction> transactions) {
    	List<String> transactionHashs= new ArrayList<>();
    	for(Transaction transaction: transactions) {
    		
    		String encryptedTransaction = Encrypt.encryptThisTransaction(transaction);
    		
    		
    	}
    	return transactionHashs;
    }
    
    private String hash(String input) {
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

