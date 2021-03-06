package amazon.oa.y2017;

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
in a graph, you can dfs to preorderFind path to node1, then dfs to preorderFind path to node 2.
then preorderFind the last common node in both path
*/