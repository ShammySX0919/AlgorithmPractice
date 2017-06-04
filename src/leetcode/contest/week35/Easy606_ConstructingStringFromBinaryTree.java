package leetcode.contest.week35;

public class Easy606_ConstructingStringFromBinaryTree {
	
	static public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public String tree2str(TreeNode t) {
		StringBuilder res = new StringBuilder();
		preorderDFS(t, res, false);
		return res.toString();
	}

	private void preorderDFS(TreeNode t, StringBuilder res, boolean notSkip) {
		boolean atRoot = false;
		if (t == null) {
			if (notSkip) {
				res.append("()");
			}
			return;
		}
		// root
		if (res.length() == 0) {
			res.append(t.val);
			atRoot = true;
		} else
			res.append("(").append(t.val);
		// if both child are null, not adding any parenthese
		if (t.left != null || t.right != null) {
			preorderDFS(t.left, res, t.right == null ? false : true);
			preorderDFS(t.right, res, false);
		}
		if (!atRoot) {
			res.append(")");
		}
	}
}
/**
606. Construct String from Binary Tree

User Accepted: 0
User Tried: 0
Total Accepted: 0
Total Submissions: 0
Difficulty: Easy

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:

Input: Binary tree: [1,2,3,4]
   1
 /   \
2     3
/    
4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".

Example 2:

Input: Binary tree: [1,2,3,null,4]
   1
 /   \
2     3
 \  
  4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

*/