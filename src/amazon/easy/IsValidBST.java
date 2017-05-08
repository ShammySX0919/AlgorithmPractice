package amazon.easy;

/**
 * Created by andrew on 5/7/2017.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class IsValidBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isValidBst(TreeNode root){
        return isValidBstRec(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private boolean isValidBstRec(TreeNode root, int minValue, int maxValue) {
        if(root==null)return true;//null tree is valid bst
        if(root.val<=minValue ||root.val>maxValue)return false;
        return isValidBstRec(root.left,minValue,root.val)&& isValidBstRec(root.right,root.val,maxValue);
    }
}
