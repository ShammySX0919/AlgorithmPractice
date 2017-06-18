package leetcode.contest.week37;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by andrew on 6/17/2017.
 */
public class AddOneRwoToTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d==1){
            TreeNode n = new TreeNode(v);
            n.left=root;
            return n;
        }
        List<TreeNode> lvlDParents = new ArrayList<>();
        collectLvlDParents(root,lvlDParents,d);
        for(TreeNode p:lvlDParents){
            TreeNode l = new TreeNode(v);
            TreeNode r = new TreeNode(v);
            l.left = p.left;
            r.right = p.right;
            p.left = l;p.right=r;
        }
        return root;
    }

    private void collectLvlDParents(TreeNode root, List<TreeNode> lvlDParents, int d) {
        int lvl = 1;
        if(d-1==1){
            lvlDParents.add(root);
            return;
        }
        int curLvlCnt = 1;//lastnode and its children as next level's last node is not right, because lastnode may have no children
        //use cur lvl and next lvl cnt will always be right
        int nextLvlCnt = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode lastNode = root;
        while(!q.isEmpty()){
            TreeNode cn = q.poll();
            curLvlCnt--;
            if(cn.left!=null){ q.add(cn.left);nextLvlCnt++;}
            if(cn.right!=null){ q.add(cn.right);nextLvlCnt++;}
            if(curLvlCnt==0 ){
                curLvlCnt = nextLvlCnt;
                nextLvlCnt=0;
                lvl++;//lvl is now next level
                //when it goes to a level, that means that level's elements have been added to queue
                //and queue contains only that level's elements
                if(lvl==d-1)break;//it needs to stop at d's parent level in order to insert row at d level
            }

        }
        lvlDParents.addAll(q);
    }
    public static void main(String[] args){

    }
}
