package leetcode.contest.week40;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class AvergageOfTreeLevel_637 {
	
	public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root==null)return res;
        int curLvl=1,nxtLvl=0,preLvl=curLvl;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        double sum = 0;
        
        while(!q.isEmpty()){
            TreeNode cNode = q.poll();
            sum+=cNode.val;
            curLvl--;
            if(cNode.left!=null){
                nxtLvl++;
                q.add(cNode.left);
            }
            if(cNode.right!=null){
                nxtLvl++;
                q.add(cNode.right);
            }
            if(curLvl==0){
                double avg = sum/preLvl;
                curLvl = nxtLvl;
                preLvl = curLvl;
                nxtLvl = 0;
                res.add(avg);
                sum=0;
            }
            
        }
        return res;
    }
	public static void main(String[] args){
		TreeNode root = new TreeNode(3);
		TreeNode l =new TreeNode(9); 
		root.left = l;
		root.right = new TreeNode(20);
		l.left = new TreeNode(15);
		l.right = new TreeNode(7);
		List<Double> res = averageOfLevels(root);
		System.out.println(res);
	}
}