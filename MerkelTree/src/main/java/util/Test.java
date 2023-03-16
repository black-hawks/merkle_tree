package util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;
import blockchain.Transaction;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		List<Transaction> dataBlocks = new ArrayList<>();
		dataBlocks.add(new Transaction("abc-xyz", "xyz", "abc", 10, 1000));
		dataBlocks.add(new Transaction("xyz2-abc1", "xyz2", "abc1", 100, 1000));
		dataBlocks.add(new Transaction("xyz32-abc2", "xyz32", "abc2", 10, 1000));
		dataBlocks.add(new Transaction("xyz4-abc2", "xyz4", "abc2", 130, 1000));
		dataBlocks.add(new Transaction("abc-xyz6", "xyz", "abc", 10, 1000));
		dataBlocks.add(new Transaction("xyz2-abc17", "xyz2", "abc1", 100, 1000));
		dataBlocks.add(new Transaction("xyz32-abc28", "xyz32", "abc2", 10, 1000));
		dataBlocks.add(new Transaction("xyz4-abc2", "xyz44", "abc2", 130, 1000));
		MerkleTree tree= new MerkleTree(dataBlocks);
		MerkleTreeNode root = tree.getMerkleRoot();
		tree.printLevelOrderTraversal(root);

		System.out.println("Merkle root: " + root.getHashValue());

		LinkedHashMap<MerkleTreeNode,String> merklePath = tree.getMerklePath(3);

		System.out.print("Merkle path: ");
		//merklePath.entrySet().stream().forEach(key -> System.out.print((MerkleTreeNode)key.+" => "));

		System.out.println("Merkel Proof= "+ tree.getMerkleProof(merklePath));

	}

}
