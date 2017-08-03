package lintcode.ladder.binarytree;

import java.util.ArrayList;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
	/**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)return result;
        int curLvl = 1;
        int nxtLvl = 0;// figure it out in loop
        Queue<TreeNode> q = new java.util.LinkedList<TreeNode>();
        q.add(root);
        ArrayList<Integer> al = new ArrayList<Integer>();
        while(!q.isEmpty()){
            TreeNode n = q.remove();
            al.add(n.val);
            curLvl--;//everu remove decrease current level node remaining to process
            //adding childrens to queue for further process
            if(n.left!=null){
               q.add(n.left);
               nxtLvl++;
            }
            if(n.right!=null){
                q.add(n.right);
                nxtLvl++;
            }
            if(curLvl==0){
                //current level is processed. ready for next level: assign curLvl as nxtLvl count
            	//nxtLvl count reset to 0
                result.add(al);//adding a row of current level to result, initiate row for next level
                al = new ArrayList<Integer>();
                curLvl=nxtLvl;
                nxtLvl=0;
            }
        }
        return result;
    }
}
/*


Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
Have you met this question in a real interview?
Example

Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

 

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

*/
