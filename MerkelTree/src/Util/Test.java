package Util;

import java.util.ArrayList;
import java.util.List;

import DataStructures.MerkleTree;
import DataStructures.MerkleTreeNode;
import DataStructures.Transaction;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		List<Transaction> dataBlocks = new ArrayList<>();
		dataBlocks.add(new Transaction("abc-xyz", "xyz", "abc", 10, 1000));
		dataBlocks.add(new Transaction("xyz2-abc1", "xyz2", "abc1", 100, 1000));
		dataBlocks.add(new Transaction("xyz32-abc2", "xyz32", "abc2", 10, 1000));
		dataBlocks.add(new Transaction("xyz4-abc2", "xyz4", "abc2", 130, 1000));
		MerkleTree tree= new MerkleTree(dataBlocks);
		MerkleTreeNode root = tree.getMerkleRoot();
		tree.printLevelOrderTraversal(root);

		MerkleTree merkleTree = new MerkleTree(dataBlocks);
		System.out.println("Merkle root: " + root.getHashValue());

	}

}