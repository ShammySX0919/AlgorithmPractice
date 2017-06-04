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
