package leetcode.contest.week36;

/**
 * merge two binary tree, node by node, overlap the values
 * Created by andrew on 6/10/2017.
 */
public class MergeTwoBinaryTrees {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null && t2==null)return null;
        int newVal = (t1==null?0:t1.val)+(t2==null?0:t2.val);
        TreeNode newNode = new TreeNode(newVal);//preorder traversal
        TreeNode left = mergeTrees(t1==null?null:t1.left,t2==null?null:t2.left);
        TreeNode right = mergeTrees(t1==null?null:t1.right,t2==null?null:t2.right);
        newNode.left = left;
        newNode.right = right;
        return newNode;
    }
}
