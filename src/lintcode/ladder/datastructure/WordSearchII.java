package lintcode.ladder.datastructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 Given a matrix of lower alphabets and a dictionary.
 Find all words in the dictionary that can be found in the matrix.

 A word can start from any position in the matrix and go left/right/up/down to the adjacent position.

 * Created by andrew on 5/27/2017.
 */
public class WordSearchII {

    class TrieNode {
        String s;//this is the string this node ends with
        boolean isString;
        HashMap<Character, TrieNode> subtree;
        public TrieNode() {
            isString = false;
            subtree = new HashMap<Character, TrieNode>();
            s = "";
        }
    };
    class TrieTree{
        TrieNode root ;
        public TrieTree(TrieNode TrieNode) {
            root = TrieNode;
        }
        public void insert(String s) {
            TrieNode curNode = root;
            for (int i = 0; i < s.length(); i++) {
                if (!curNode.subtree.containsKey(s.charAt(i))) {
                    curNode.subtree.put(s.charAt(i), new TrieNode());
                }
                curNode  =  curNode.subtree.get(s.charAt(i));
            }
            curNode.s = s;//let node also contains the string so that you do not need to traversal the tree
            curNode.isString  = true;
        }
        public boolean find(String s){
            TrieNode curNode = root;
            for (int i = 0; i < s.length(); i++) {
                if (!curNode.subtree.containsKey(s.charAt(i))) {
                    return false;
                }
                curNode  =  curNode.subtree.get(s.charAt(i));
            }
            return curNode.isString ;
        }
    };

    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    //starting from each element:for for the matrix
    //to avoid duplication, use set the first, convert it to arraylist at last
    //subroutine that traverse the matrix and build words
    //stop traverse when maximum word length is reached, or done all possible traversal
    //make sure it does not going back to revisit a position
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        ArrayList<String> ans = new ArrayList<String>();
        //build trie tree to represent dictionary.
        // it's also doable to use set as dictionary because hash looking up is even faster
        TrieTree tree = new TrieTree(new TrieNode());
        for(String word : words){
            tree.insert(word);
        }
        //starting from any position in matrix
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                search(board, i, j, tree.root, ans);
            }
        }
        return ans;
        // write your code here

    }
//valid steps to preorderFind neighbors
    public int [] rd = {1, 0, -1, 0};
    public int [] cd = {0, 1, 0, -1};
    private boolean isNotValidMove(char[][] board,int x,int y){
        return x < 0 || x >= board.length || y < 0 || y >= board[0].length||board[x][y]==0;
    }
    public void search(char[][] board, int x, int y, TrieNode root, ArrayList<String> ans) {
        if(isNotValidMove(board,x,y) || root == null)
            return ;

        if(root.isString == true)
        {
            if(!ans.contains(root.s)){
                ans.add(root.s);
            }
        }

        if(root.subtree.containsKey(board[x][y])){
            char curChar = board[x][y];
            board[x][y] = 0;//mark,mark the board so remember visited cells in subsequent paths
            for(int i = 0; i < 4; i++){
                search(board, x+ rd[i], y+ cd[i], root.subtree.get(curChar), ans);
            }
            //restore the cell value for other searchs
            board[x][y] = curChar;//back tracking
        }

    }
}
