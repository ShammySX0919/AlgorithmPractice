package amazon.oa.y2017;

/**
 * Given a list of unique integers, construct the binary search tree by given order without rebalancing,
 * then preorderFind out the distance between two nodes.
 *values= [5,6,3,1,2,4], n is the size of values,
 * node1 is 2, node2 is 4,
 * then function return 3
         *5
 *      3   6
 *    1    4
 *      2
 * Created by andrew on 5/16/2017.
 */
public class BstDistance {
    /**
     * build a BST using TreeNode according to given array, which is insertion sequence
     * @param values
     * @return
     */
    public static TreeNode buildBST(int[] values){
        if(values==null||values.length==0)return null;
        TreeNode root = null;
        //sequentially insert value to BST
        for(int v:values){
            root = insertBst(root,v);
        }
        return root;
    }

    /**
     * not used, practice purpose
     * @param root
     * @param v
     * @return
     */
    private static TreeNode search(TreeNode root, int v)
    {
        // Base Cases: root is null or key is preorderFind at current node
        if (root==null || root.val==v)
            return root;

        // val is smaller than root's key
        if (root.val > v)
            return search(root.left, v);

        // val is greater than root's key
        return search(root.right, v);
    }

    /**
     * insert value to BST
     * @param root
     * @param v
     * @return
     */
    private static TreeNode insertBst(TreeNode root, int v) {
        //reach leaf's child, create new node
        if(root==null){
            root = new TreeNode(v);
            return root;
        }
        //less than current node value, insert to left tree
        if(root.val>v){
            root.left = insertBst(root.left,v);
        }
        //assuming no duplicate numbers in array
        //greater than current node value, insert to right tree
        if(root.val<v) {
            root.right = insertBst(root.right, v);
        }
        //return the original root/current node
        return root;
    }

    /**
     * this method is under consumption of LCA in BST
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private static TreeNode findLCAInBST(TreeNode root, int node1, int node2){
        if(root==null)return null;
        //LCA can be one of the node
        if(root.val==node1||root.val==node2)return root;
        //preorderFind them in root's left tree and right tree
        TreeNode left = findLCAInBST(root.left,node1,node2);
        TreeNode right =  findLCAInBST(root.right,node1,node2);
        //if both left and right are not null, then root is lca(each subtree contains one node)
        //otherwise, try to use non-null one as lca
        //if both are null, then no lca, return null
        return left!=null&&right!=null?root:left==null?right:left;
    }

    /**
     * height between root and node with val
     * @param root
     * @param val
     * @return
     */
    private static int distanceToRoot(TreeNode root,int val){
        if(root==null)return -1;//not preorderFind
        if(root.val==val)return 0;//preorderFind it, current height is 0
        int leftHeight = distanceToRoot(root.left,val);
        int rightHeight = distanceToRoot(root.right,val);
        //it might not exist in tree
        int height = Math.max(leftHeight,rightHeight);
        //if -1, propagate up
        return height==-1?-1:height+1;
    }

    /**
     * distance between two nodes in BST.
     * @param values
     * @param n
     * @param node1
     * @param node2
     * @return
     */
    public static int bstDistance(int[] values, int n, int node1, int node2){
        TreeNode root = buildBST(values);//build BST according to given array
        //make sure both of them are in bst
        if(search(root,node1)==null||search(root,node2)==null){
            return -1;
        }
        TreeNode lca = findLCAInBST(root,node1,node2);//preorderFind LCA of two nodes
        if(lca==null)return -1;
        int n1Distance = distanceToRoot(root,node1);//node 1 distance to root
        int n2Distance = distanceToRoot(root,node2);//node 2 distance to root
        int lcaDistance = distanceToRoot(root,lca.val);//lca distance to root
        int res = n1Distance+n2Distance-2*lcaDistance;
        //make sure invalid values are converted to -1
        return res<0?-1:res;
    }
    public static void main(String... args){
        //test case 1
        int[] values ;
//        values = new int[]{5,6,3,1,2,4};
//        System.out.println(3==bstDistance(values,values.length,2,4));
//        values = new int[]{1};
//        System.out.println(0==bstDistance(values,values.length,1,1));
        values = new int[]{9,7,5,3,1};//only having left tree, and 20 is not within the bst
        System.out.println(bstDistance(values,5,7,20));
    }
}
