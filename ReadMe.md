<h1>Merkle Tree Implementation in Blockchain</h1>

Blockchain is a decentralized digital ledger technology that allows multiple parties to store and share data in a secure and transparent way without the need for intermediaries. In simple terms, it is a digital ledger of transactions that is maintained by a network of computers. Each block in the chain contains a cryptographic hash of the previous block, along with a timestamp and transaction data. Once a block is added to the chain, it cannot be altered, ensuring the integrity of the data stored on the blockchain.

As the blockchain grows, verifying the data stored on it becomes more time-consuming and resource-intensive. Merkle trees address this issue by allowing for a more efficient verification process that only requires a subset of the data to be verified. A Merkle tree is a binary tree of hashes, where each leaf node represents a hash of a data block, and each non-leaf node represents a hash of its child nodes. In a blockchain, a Merkle tree is used to summarize all transactions in a block, allowing for efficient validation of the block.

This project is an implementation of Merkle Tree in Blockchain. It demonstrates how a Merkle Tree is used to validate transactions in a blockchain network.To create a Merkle tree, we first hash each transaction and store the hashes. We then pair adjacent hashes and hash them together, storing the resulting hash. We repeat this process, pairing adjacent hashes, until we have a single hash, known as the Merkle root.

To verify the presence of a block in a blockchain, a user can obtain the block's Merkle hash from a trusted source and then generate a Merkle tree from the transactions in the block. Using the Merkle root of this generated tree, the user can verify its presence in the blockchain. This method is efficient and reduces the overhead of checking for the transactions in a huge list of transactions.

<h3>Contributors:</h3>

Vivek Murarka (22200673)

Nikhitha Grace Josh (22200726)

Purvish Shah (22200112)

Ravi Raj Pedada (22200547)

Meghana Kamsetty Ravikumar (22200568)
