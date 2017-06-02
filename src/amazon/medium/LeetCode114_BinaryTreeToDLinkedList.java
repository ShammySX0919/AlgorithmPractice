package amazon.medium;

/**
 * Created by 212595974 on 6/2/2017.
 */

public class LeetCode114_BinaryTreeToDLinkedList {
    //this node is both a tree node and node of doubly linked list
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root;//this is what being returned
//post order
public void flatten(Node root) {
    if (root == null) return;

    Node left = root.left;
    Node right = root.right;
//just build the right tree
    root.left = null;
//process left
    flatten(left);
    //process right
    flatten(right);
//append left to root's right
    root.right = left;
    Node cur = root;
    //move to right most
    while (cur.right != null) cur = cur.right;
    //then append right to list
    cur.right = right;
}
//in order
    Node bintree2listUtil(Node node) {
        // Base case
        if (node == null)
            return node;

        // Convert the left subtree and link to root
        if (node.left != null) {
            // Convert the left subtree
            Node left = bintree2listUtil(node.left);

            // move left to its right side
            for (; left.right != null; left = left.right) ;

            //this is in-order traversal, link current node with left result
            //by linking current node as right child of left list
            left.right = node;
            // and current node's left child pointiing to left list
            node.left = left;
        }

        // then continue process right child
        if (node.right != null) {
            // Convert the right subtree
            Node right = bintree2listUtil(node.right);

            // link right list with current node
            for (; right.left != null; right = right.left) ;
            right.left = node;
            node.right = right;
        }

        return node;
    }

    // The main function that first calls bintree2listUtil(), then follows
    // step 3 of the above algorithm

    Node bintree2list(Node node) {
        // Base case
        if (node == null)
            return node;

        // Convert to DLL using bintree2listUtil()
        node = bintree2listUtil(node);

        // after in-order process, the node returned is actually pointing to middle of list
        //find its left most node
        while (node.left != null)
            node = node.left;
    //return it as head of convereted list
        return node;
    }

    /* Function to print nodes in a given doubly linked list */
    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    /* Driver program to test above functions*/
    public static void main(String[] args) {
        LeetCode114_BinaryTreeToDLinkedList tree = new LeetCode114_BinaryTreeToDLinkedList();

        // Let us create the tree shown in above diagram
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        // Convert to DLL
        Node head = tree.bintree2list(tree.root);

        // Print the converted list
        tree.printList(head);
    }
}
