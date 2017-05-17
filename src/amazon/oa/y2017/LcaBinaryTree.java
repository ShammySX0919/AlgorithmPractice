package amazon.oa.y2017;
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/#/description

class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
}
/**
 * it assumes p and q are in tree
 * @author Andrew
 *
 */
public class LcaBinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    if(root == null) return null;
	    if(root == p || root == q) return root;
	    TreeNode left = lowestCommonAncestor(root.left, p, q);
	    TreeNode right = lowestCommonAncestor(root.right, p, q);
	    return left != null && right != null ? root : left == null ? right : left; 
	}
}
/**
in a graph, you can dfs to find path to node1, then dfs to find path to node 2.
then find the last common node in both path
*/