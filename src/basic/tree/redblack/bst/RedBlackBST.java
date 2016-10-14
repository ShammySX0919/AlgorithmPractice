package basic.tree.redblack.bst;
/**
 * this is left leaning red black tree
 * 
 * this is copied form http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 * good demonstration on reducing. reducing once case to another, transform complex situation to simpler base case.
 * so modular programming is actually reduction programming.
 * 
 * When do node searching, you use same elemetary BST search, forget about colors. colors are used in modification to make
 * tree balanced.
 * 
 * color in this implementation is color of link. since each node has only one link to goes in, there is no need to define
 * explicit link object or variable. we keep its color to node it points to. We define null node has black link goes in 
 * 
 * left rotation is demonstrated in vidoe at 8 minutes. very good one.
 * 
 * Basic strategy: maintain 1-1 correspondence with 2-3 trees by applying elementary red-black BST operations.
 * do not allow adjacent red links. black links can be adjacent
 * Insertion: always insert red link node, then rotate or flip correspondingly afterward
 * 1. tree with one node:--this applies to leaf node since leaf node is a one node tree if you just check itself
 * 		1.1 new node is smaller than root, then insert red linked node directly to left child. done
 * 		1.2 new node is greater than root, then insert red linked node as right child. 
 * 			but since red link goes only to left, then do a left rotation to make it valid
 * 2. insert into 2 nodes tree
 * 3. insert into 3 nodes tree
 * 
 * @author Andrew Ma
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST <Key extends Comparable<Key>,Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root; //root of BST
	/**
	 * when you organize key and value in BST, this BST becomes an implementation of symbol table
	 * @author Andrew Ma
	 *
	 */
	private class Node{
		private Key key;// key of node
		private Value val;//associated data
		private Node left, right;//links to left and right subtrees
		private boolean color;//color of parent link
		private int size;// subtree count
		
		public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
	}
	/*
	 * check if parent link is red or black.
	 * null node has black parent link.
	 */
	private boolean isRed(Node x){
		//null links are black
		if (x==null)return false;
		return x.color==RED;
	}
	//number of node in substree rooted at x. 0 if x is null
	private int size(Node x){
		if(x==null)return 0;
		return x.size;
	}
	public int size(){
		return size(root);
	}
	public boolean isEmpty() {
        return root == null;
    }
	//
	public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null");
        return get(root, key);
    }
	/**
	 * basic searching algorithm of BST.
	 * keep moving node cursor to next valid position. by doing this, each time, 
	 * it gets rid of half of content to search.(narrows down half of remaining searching scope)
	 * @param x
	 * @param key
	 * @return
	 */
	private Value get(Node x, Key key){
		while(x!=null){
			int cmp = key.compareTo(x.key);
			//key is less than node's key, goes to left subtree
			if(cmp<0)x=x.left;
			//key is greater than node's key, move to right subtree to continue the search
			else if(cmp>0)x=x.right;
			else //if(cmp==0)
				return x.val;
		}
		return null;
	}
	
}
