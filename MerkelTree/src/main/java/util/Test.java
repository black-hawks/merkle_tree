package util;

import blockchain.Transaction;
import dataStructure.merkleTree.MerkleTree;
import dataStructure.merkleTree.MerkleTreeNode;
import dataStructure.merkleTree.NodeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
//		dataBlocks.add(new Transaction("xyz32-abc28", "xyz32", "abc2", 10, 1000));
//		dataBlocks.add(new Transaction("xyz4-abc2", "xyz44", "abc2", 130, 1000));
        MerkleTreeNode root = MerkleTree.generateMerkleRoot(dataBlocks);
//        TreePrinter.printTree(root);
//		tree.printLevelOrderTraversal(root);

//        System.out.println("Merkle root: " + root.getHashValue());
//        System.out.println(tree.getHeight(root));
        Stack<Map.Entry<MerkleTreeNode, NodeDirection>> merklePath = MerkleTree.getMerklePath(root, 2);
//
//		System.out.print("Merkle path: ");
//        merklePath.forEach((key1, value) -> System.out.print(key1.substring(0, 4) + " => "));
        for (Map.Entry<MerkleTreeNode, NodeDirection> element : merklePath) {
            System.out.print(element.getKey().getHashValue().substring(0, 4) + " => ");
        }
//        System.out.println("Merkel Proof= " + tree.verifyMerkleProof(merklePath));

    }

}
