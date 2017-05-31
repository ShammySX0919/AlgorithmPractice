package amazon;

/**
 * http://algorithms.tutorialhorizon.com/find-the-deepest-left-node-in-a-binary-tree/
 *
 Take two global vari­able as “deepestlevel” and ” deepLeftNode”.
 start­ing with level=0, Do the inorder tra­ver­sal and when­ever you go down one level
    ( root.left OR root.right), increase the level by 1.
 Keep check­ing if cur­rent node is the left child and deep­estlevel < level,
    if yes then update the “deep­estlevel ” and ” deepLeft­N­ode “.
 At the end return ” deepLeftNode “, which will the deep­est node value.

 * Created by andrew on 5/30/2017.
 */
public class DeepestLeftnodeInBinaryTree {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public int deepestLevel = 0;
    public int deepLeftNode;

    public int deepLeft(Node root) {
        preorderFind(root, 0, true);
        return deepLeftNode;
    }

    public void preorderFind(Node curNode, int level, boolean isLeftNode) {
        if (curNode != null) {
            preorderFind(curNode.left, ++level, true);
            if (isLeftNode && deepestLevel < level) {// check if it a left child and
                // current level is greater than deepest level
                deepLeftNode = curNode.data; // update the deepest left node
                deepestLevel = level;   // update the deepest level
            }
            preorderFind(curNode.right, level, false);
        }
    }

    public static void main(String args[]) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.right = new Node(8);

        DeepestLeftnodeInBinaryTree dp = new DeepestLeftnodeInBinaryTree();
        System.out.println("Deapest Left child is: " + dp.deepLeft(root));

    }
}
