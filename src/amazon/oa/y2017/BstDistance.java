package amazon.oa.y2017;

/**
 * Given a list of unique integers, construct the binary tree by given order without rebalancing, then find out the distance between two nodes.
 *values= [5,6,3,1,2,4], n is the size of values, node1 is 2, node2 is 4, then function return 3
 * Created by andrew on 5/16/2017.
 */

import sun.reflect.generics.tree.Tree;

/**
 * algorithm: node1-root + node2-root - 2*lca-root
 */
/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
*/
public class BstDistance {
    //public static int bstDistance(int[] values, int n, int node1, int node2){}
    public static int bstDistance(TreeNode root, int val1, int val2){
        int distTo1 = findHeight(root,val1);
        int distTo2 = findHeight(root,val2);
        TreeNode lca = findLCA(root,val1,val2);
        int distToLca = findHeight(root,lca.val);
        return distTo1+distTo2-2*distToLca;
    }
    public static int findHeight(TreeNode root, int val){
        if(root==null)return -1;//-1 means not find
        if(root.val==val)return 0;
        int leftHeight = findHeight(root.left,val);
        int rightHeight = findHeight(root.right,val);
        if(leftHeight==-1 && rightHeight==-1)return -1;
        return Math.max(leftHeight,rightHeight)+1;
    }
    public static TreeNode findLCA(TreeNode root, int val1, int val2){
        if(root==null)return null;
//if it is not bst, but binary tree instead, different method has to be used
        if(root.val>val1 && root.val>val2){
            return findLCA(root.left,val1,val2);
        }else if(root.val<val1 && root.val<val2){
            return findLCA(root.right,val1,val2);
        }else
            return root;
        /*
        if(root.val==val1 ||root.val=val2)return root;
        TreeNode left = findLCA(root.left,val1,val2);
        TreeNode right = findLCA(root.right,val1,val2);
        return left!=null&&right!=null?root:left==null?right:left;
        */
    }
}
