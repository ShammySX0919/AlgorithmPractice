package lintcode.ladder.binarytree;

import java.util.ArrayList;

public class BinaryTreePreorderTraversal {
	class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	/**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> al = new ArrayList<Integer>();
        preOrderTraversal(root,al);
        return al;
    }
    void preOrderTraversal(TreeNode root,ArrayList<Integer> al){
        if(root==null)return;
        al.add(root.val);//acccess root the first
        preOrderTraversal(root.left,al);//then left child
        preOrderTraversal(root.right,al);//then right child
    }

}
/*


Given a binary tree, return the preorder traversal of its nodes' values.
Have you met this question in a real interview?
Example

Given:

    1
   / \
  2   3
 / \
4   5

return [1,2,4,5,3].

*/