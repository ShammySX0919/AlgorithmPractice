package leetcode.contest.week31;

/**
 * Created by andrew on 5/6/2017.
 */

  //Definition for a binary tree node.
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


public class No572_SubtreeOfAnotherTree {
    /*
    public boolean isSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(s);
        TreeNode startS = findTRootInS(t,q);
        if(startS==null)return false;
        while(startS!=null ){
            if(isSub(startS,t))return true;
            if(q.isEmpty())break;
            startS = findTRootInS(t,q);
        }
        return false;
    }

    private boolean isSub(TreeNode sameT, TreeNode t) {
        if(sameT==null && t==null)return true;
        if(sameT==null&&t!=null||sameT!=null&&t==null)return false;
        if(sameT.val!=t.val)return false;

        return isSub(sameT.left,t.left) &&isSub(sameT.right,t.right);
    }

    private TreeNode findTRootInS(TreeNode t,Queue<TreeNode> q){
        while(!q.isEmpty()){
            TreeNode cNode = q.poll();
            if(cNode.left!=null)q.add(cNode.left);
            if(cNode.right!=null)q.add(cNode.right);
            if(t.val== cNode.val)
                return cNode;

        }
        return null;
    }
*/
    /**
     * here is a better solution.
     * it use # for null, so that it makes sure subtree is exactly the same
     * */
     public boolean isSubtree(TreeNode s, TreeNode t) {
        return serialize(s).contains(serialize(t));
     }

    /**
     * this method is for passing shared string builder
     * @param root
     * @return
     */
     public String serialize(TreeNode root) {
         StringBuilder res = new StringBuilder();
         serialize(root, res);
         return res.toString();
     }

     private void serialize(TreeNode root, StringBuilder res) {
     if (root == null) {res.append(",#"); return;}//smart move
     res.append("," + root.val);//since we compare value to see if node is same, val is what we are interested in
     serialize(root.left, res);//covers null
     serialize(root.right, res);
     }

    public static void main(String[] args){
        No572_SubtreeOfAnotherTree o = new No572_SubtreeOfAnotherTree();
//        TreeNode s = new TreeNode(3);
//        s.left = new TreeNode(4);
//        s.right = new TreeNode(5);
//        s.left.left = new TreeNode(1);
//        s.left.right = new TreeNode(2);
//
//        TreeNode t = new TreeNode(4);
//        t.left = new TreeNode(1);
//        t.right = new TreeNode(2);
                TreeNode s = new TreeNode(1);
        s.left = new TreeNode(1);
        TreeNode t = new TreeNode(1);
        System.out.println(o.isSubtree(s,t));
    }
}
