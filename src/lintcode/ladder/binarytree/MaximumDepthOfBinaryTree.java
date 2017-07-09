package lintcode.ladder.binarytree;

public class MaximumDepthOfBinaryTree {
	 /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if(root==null)return 0;//we are counting number of nodes in a path.no node means 0 node.
        if(root.left==null&&root.right==null)return 1;//depth is defined as number of nodes along the path. 
        //height is number of links and thus depth-1
        int ld =maxDepth(root.left);
        int rd = maxDepth(root.right);
        return Math.max(ld,rd)+1;//+1 is root it self
    }
}
/*


Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example

Given a binary tree as follow:

  1
 / \ 
2   3
   / \
  4   5

The maximum depth is 3.

*/