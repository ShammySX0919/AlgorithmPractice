package lintcode.ladder.binarytree;

public class ValidateBinarySearchTree {

	/*
	 Algorithm 2: For a node root, we know that all nodes in the left subtree is smaller than the value of root. 
	 We can pass this value to the left subtree, checking if the value of its left child is smaller than this value. 
	 Then we will update this passing value to the value of left child and keep passing it to next level. 
	 For the right subtree, we need to make sure that all values in the right subtree is larger than the value of root.
	 In conclusion, we should pass two values, which is min and max, to the next level, and compare the value of nodes. 
	 Update the min and max, and pass it to next level until we reach the leaf node.

	Algorithm 3: The in-order traverse can helps us. Doing a in-order traverse on a BST, the output will be a 
	increasing sequence.
	*/

	    /**
	     * @param root: The root of binary tree.
	     * @return: True if the binary tree is BST, or false
	     */
		private boolean isValidBST(TreeNode root, int min, int max) {
	        if (root == null)
	            return true;
	        if (root.val <= min || root.val >= max)
	            return false;
	        return isValidBST(root.left, min, Math.min(max, root.val))
	        && isValidBST(root.right, Math.max(min, root.val), max);
	    }
	    public boolean isValidBST(TreeNode root) {
	        // write your code here
	        /*
	       return isValidBST(root, Integer.MIN_VALUE,Integer.MAX_VALUE);
	         */
	    	return inorderTraverse(root);
	    }
	    
	    TreeNode prev = null;//key, it means previous root for both left and root
	    
	    public boolean inorderTraverse(TreeNode root) {
	        if (root == null)
	            return true;
	        //left child
	        if (!inorderTraverse(root.left))
	            return false;
	        //logic for current node
	        if (prev != null) {
	            if (root.val <= prev.val)
	                return false;
	        }
	        
	        prev = root;//in order
	        //right child
	        if (!inorderTraverse(root.right))
	            return false;
	        
	        return true;
	    }
	}
/*


Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.
    A single node tree is a BST

Have you met this question in a real interview?
Example

An example:

  2
 / \
1   4
   / \
  3   5

The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).

*/