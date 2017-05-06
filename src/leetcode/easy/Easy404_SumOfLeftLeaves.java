package leetcode.easy;

/**
 * Created by Andrew Ma on 10/25/2016.
 */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

public class Easy404_SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null)return 0;
        int sum=0;
        //if root.left is leave
        if(root.left!=null&&(root.left.left==null && root.left.right==null)){
            sum=root.left.val;
        }
        sum+=sumOfLeftLeaves(root.left)+sumOfLeftLeaves(root.right);
        return sum;
    }
}