package lintcode.ladder.binarytree;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
	class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		          this.left = this.right = null;
		      }
		  }
	/**
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
     int preStart = 0;
    int preEnd = preorder.length-1;
    int inStart = 0;
    int inEnd = inorder.length-1;
 
    return construct(preorder, preStart, preEnd, inorder, inStart, inEnd);
}
 
public TreeNode construct(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
    if(preStart>preEnd||inStart>inEnd){
        return null;
    }
 
    int val = preorder[preStart];//get root
    TreeNode p = new TreeNode(val);//build root
 
    //find root element index from in-order
    int k=0;//k is root index in in-order array. it's not always balanced so that use basic search method for it.
    for(int i=0; i<inorder.length; i++){
        if(val == inorder[i]){
            k=i;
            break;
        }
    }
 //build left tree
    //k is root index in in-order, that means its left k-1 elements are corresponding preorder elements
    //for in--order, the left k-1 elements are its child tree
    //for pre-order, a node's following k-1 is its child tree
    //there are totally k-1-inStart+1 = k-inStart elements
    //preStart+1 is because the root should be excluded for children building
    
    p.left = construct(preorder, preStart+1, preStart+(k-inStart), inorder, inStart, k-1);
//build right tree
    p.right= construct(preorder, preStart+(k-inStart)+1, preEnd, inorder, k+1 , inEnd);
 
    return p;
}
}
/*


Given preorder and inorder traversal of a tree, construct the binary tree.

Notice

You may assume that duplicates do not exist in the tree.

Example

Given in-order [1,2,3] and pre-order [2,1,3], return a tree:

  2
 / \
1   3


*/