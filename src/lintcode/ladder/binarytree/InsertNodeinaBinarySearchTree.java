package lintcode.ladder.binarytree;

public class InsertNodeinaBinarySearchTree {
	/**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
    	//this structure is to make sure a node's left and right are correct--including new node correctly
    	if (root == null) {
            return node;//acting itself as root. 
            //when it's returned, it will be attached to correct left or right child or parent node
        }
        if (root.val > node.val) {
            //insert to left tree
            //returned node is either original link or new node to link to!
            root.left = insertNode(root.left, node);
        } else {
            //else insert to right tree
            root.right = insertNode(root.right, node);
        }
        return root;//key
    }
}
/*


Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

Notice

You can assume there is no duplicate values in this tree + node.

Example

Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6


*/