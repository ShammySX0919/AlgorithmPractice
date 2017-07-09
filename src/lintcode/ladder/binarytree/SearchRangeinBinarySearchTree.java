package lintcode.ladder.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * I was asked time complexity of finding k element in binary search tree and said O(klgn)
 * which is wrong if it is not finding them randomly(wrost case), for a range search
 * it is still O(n)
 * @author Andrew
 *
 */
public class SearchRangeinBinarySearchTree {
	int cnt=0;
	/**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null)return result;
        //it requires to return values in ascending order, so choose in order for that purpose
        inOrderTraversal(root,k1,k2,result);
        return result;
    }
    //using post order to stop left and right earlier
    void inOrderTraversal(TreeNode root,int k1, int k2,ArrayList<Integer> al){
        if(root==null)return ;
        System.out.println(++cnt);
        if(root.val>k1)inOrderTraversal(root.left,k1,k2,al);
        if(root.val>=k1 && root.val<=k2)
            al.add(root.val);
        if(root.val<k2)inOrderTraversal(root.right,k1,k2,al);
        //if(al.size()==(k2-k1+1))return;
        return ;
    }
    public static void main(String[] arg){
    	TreeNode root = new TreeNode(9);
		TreeNode l =new TreeNode(7); 
		root.left = l;
		root.right = new TreeNode(20);
		l.left = new TreeNode(5);
		l.right = new TreeNode(8);
		
		SearchRangeinBinarySearchTree o = new SearchRangeinBinarySearchTree();
		List<Integer> res = o.searchRange(root, 5, 9);
		System.out.println(res);
    }
}
/*
		9
		/\
	   7  20
	  /\
     5 8
*/