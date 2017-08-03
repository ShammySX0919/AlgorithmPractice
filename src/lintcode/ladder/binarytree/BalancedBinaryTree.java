package lintcode.ladder.binarytree;

public class BalancedBinaryTree {
	/**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if(root==null)return true;//this is important, null tree is balanced. beccause otherwise it will propagate up and make all other wrong
        //subtrees of every node
        if (Math.abs(getHighth(root.left)-getHighth(root.right))>1)return false; 
        //here, the root is balanced
        return isBalanced(root.left) &&isBalanced(root.right);
    }
    int getHighth(TreeNode root){
        if(root==null)return 0;
        if(root.left==null&&root.right==null)return 1;
        int lh = getHighth(root.left);
        int rh = getHighth(root.right);
        return Math.max(lh,rh)+1;
        
    }
}
/*


Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
Have you met this question in a real interview?
Example

Given binary tree A = {3,9,20,#,#,15,7}, B = {3,#,20,15,7}

A)  3            B)    3 
   / \                  \
  9  20                 20
    /  \                / \
   15   7              15  7

The binary tree A is a height-balanced binary tree, but B is not.

*/