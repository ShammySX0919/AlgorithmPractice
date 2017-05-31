package lintcode.ladder.binarytree;

/**
 * Given a root of Binary Search Tree with unique value for each node.
 * Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing.
 * You should keep the tree still a binary search tree after removal.
 * Created by 212595974 on 5/30/2017.
 */
class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
}
/*
 Delete node 有三种情况

因为要delete,在find这个node的过程中要保留一个parent的变量

    leaf node

删掉这个node，把parent对这个node的reference设为null

    Node with only one child

delete the node,把parent对node的reference link到node的child

    Node with 2 children
        find the minimum node of right subtree
        replace the value of found node
        delete the old duplicate node(case 1/2, cause minimum node should not have left child)
*/

public class RemoveNode {
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;

        TreeNode parent = findNode(dummy, root, value);
        TreeNode node;
        if (parent.left != null && parent.left.val == value) {
            node = parent.left;
        } else if (parent.right != null && parent.right.val == value) {
            node = parent.right;
        } else {
            return dummy.left;
        }

        deleteNode(parent, node);

        return dummy.left;
    }
    private TreeNode findNode(TreeNode parent, TreeNode node, int value) {
        if (node == null) {
            return parent;
        }

        if (node.val == value) {
            return parent;
        }
        if (value < node.val) {
            return findNode(node, node.left, value);
        } else {
            return findNode(node, node.right, value);
        }
    }
    private void deleteNode(TreeNode parent, TreeNode node) {
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            TreeNode temp = node.right;
            TreeNode father = node;

            while (temp.left != null) {
                father = temp;
                temp = temp.left;
            }

            if (father.left == temp) {
                father.left = temp.right;
            } else {
                father.right = temp.right;
            }

            if (parent.left == node) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }

            temp.left = node.left;
            temp.right = node.right;
        }
    }
}
