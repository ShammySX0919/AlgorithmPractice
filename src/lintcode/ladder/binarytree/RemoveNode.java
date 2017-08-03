package lintcode.ladder.binarytree;

/**
 * Given a root of Binary Search Tree with unique value for each node.
 * Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing.
 * You should keep the tree still a binary search tree after removal.
 * Created by 212595974 on 5/30/2017.
 */
/*
*/

public class RemoveNode {
	
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;//give root also a parent so that logic could be consistent

        TreeNode parent = findParentNode(dummy, root, value);
        TreeNode node;
        if (parent.left != null && parent.left.val == value) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            node = parent.right;
        } else {
            return dummy.left;
        }

        deleteNodeFromParent(parent, node);

        return dummy.left;
    }
    private TreeNode findParentNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) {
            return parent;
        }

        if (node.val == value) {
            return parent;
        }
        if (value < node.val) {
            return findParentNode(node, node.left, value);
        } else {
            return findParentNode(node, node.right, value);
        }
    }
    
    private void deleteNodeFromParent(TreeNode parent, TreeNode node) {
    	//for node having no right child,need to deal with its left child with its parent node
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        }//node having right node, that means it also has left node 
        else {
        	//fins minimum node in node's right tree
            TreeNode temp = node.right;
            TreeNode father = node;
            //find left most node, that will be used to replace node which is to be deleted
            while (temp.left != null) {
                father = temp;
                temp = temp.left;
            }
            //need to mount temp's children to its parent correctly
            //temp is the left most node, so that it has no left child, and that's condition the above loop exits
            //replace temp with its right child(temp can be left or right child of its parent)
            if (father.left == temp) {
                father.left = temp.right;
            } else {
                father.right = temp.right;
            }
            //move left most node to replace node
            if (parent.left == node) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }
            //mount node's left and right tree to new node
            temp.left = node.left;
            temp.right = node.right;
        }
    }
}
