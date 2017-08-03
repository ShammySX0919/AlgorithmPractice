package lintcode.ladder.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//I will use DFS to serielize and deserielize the tree
public class BinaryTreeSerialization {
	
	class TreeNode {
		      public int val;
		      public TreeNode left, right;
		      public TreeNode(int val) {
		          this.val = val;
		         this.left = this.right = null;
		      }
		  }
	/**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        dfsBuildString(root, sb);
        sb.deleteCharAt(sb.length()-1);//remove last ,
        return sb.toString();
    }
 /**
  * use # for null node so that nothing will be left out
  * @param node
  * @param sb
  */
    private void dfsBuildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
        } else {
            sb.append(node.val + ",");//pre order
            dfsBuildString(node.left, sb);//left
            dfsBuildString(node.right,sb);//right
        }
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if(data==null||data.length()==0)return null;
        // write your code here
        Queue<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return dfsBuildTree(nodes);
    }
    private TreeNode dfsBuildTree(Queue<String> nodes) {
        String val = nodes.remove();//using queue to easily remove nodes
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(val));//build out a root node. pre-order
        node.left = dfsBuildTree(nodes);///build left. next is left child
        node.right = dfsBuildTree(nodes);//build right tree//next is right child
        return node;
    }
}
/*


Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 
'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

Notice

There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.

Example

An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

  3
 / \
9  20
  /  \
 15   7

Our data serialization use BFS traversal. This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

*/