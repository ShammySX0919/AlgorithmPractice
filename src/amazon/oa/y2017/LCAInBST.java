package amazon.oa.y2017;

/**
 * leetcode Lowest Common Ancestor of a Binary Tree Created by andrew on
 * 5/16/2017.
 */
public class LCAInBST {
	Node root;

	/*
	 * Function to preorderFind LCA of n1 and n2. The function assumes that both n1 and
	 * n2 are present in BST
	 */
	Node lcaInBSTRec(Node node, int n1, int n2) {
		if (node == null)
			return null;

		// If both n1 and n2 are smaller than root, then LCA lies in left
		if (node.data > n1 && node.data > n2)
			return lcaInBSTRec(node.left, n1, n2);

		// If both n1 and n2 are greater than root, then LCA lies in right
		if (node.data < n1 && node.data < n2)
			return lcaInBSTRec(node.right, n1, n2);

		return node;
	}

	Node lcaInBSTIter(Node node, int n1, int n2) {
		while (node != null) {

			// If both n1 and n2 are smaller than root, then LCA lies in left
			if (node.data > n1 && node.data > n2)
				node = node.left;

			// If both n1 and n2 are greater than root, then LCA lies in right
			else if (node.data < n1 && node.data < n2)
				node = node.right;

			else
				break;
		}
		return node;
	}
}

// A binary tree node
class Node {
	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right = null;
	}
}