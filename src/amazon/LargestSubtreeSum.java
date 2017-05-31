package amazon;

/**
 * Given a binary tree, every node has a int value,
 * return the root node of subtree with the largest sum up value
 *
 * We can use post-order traversal to preorderFind the sum of left and right subtree
 and sum of leftsum + rightsum +rootdata is passed above to the parent of tree.
 Maintain a node to root of largest subtree found so far and update it whenever
 greater sum is found.

 Bottom-up approach. Time complexity-O(n).Stack space-O(n)

 * Created by andrew on 5/30/2017.
 */
public class LargestSubtreeSum {
    static class Node{
        int data;
        Node left,right;
        public Node(int d){
            this.data = d;
        }
    }
    Node res = null;
    int maxSum = 0;
    Node maxSumSubtree(Node root)
    {
        if(root==null) return null;
        //single node
        if(root.left==null && root.right==null) return root;

        int maxsum=0;
        helper(root);
        return res;
    }
    //post order traversal and sum up
    int helper(Node p)
    {
        if(p==null) return 0;
        int lsum = helper(p.left);
        int rsum = helper(p.right);
        int total = lsum + rsum + p.data;
        if( total > maxSum ){
            maxSum=total;
            res=p;
        }
        return total;
    }
}
