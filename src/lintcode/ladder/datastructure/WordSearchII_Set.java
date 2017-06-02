package lintcode.ladder.datastructure;

import java.util.*;

/**
 *
 Given a matrix of lower alphabets and a dictionary.
 Find all words in the dictionary that can be found in the matrix.

 A word can start from any position in the matrix and go left/right/up/down to the adjacent position.

 * Created by andrew on 5/27/2017.
 */
public class WordSearchII_Set {

    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    //starting from each element:for the matrix
    //to avoid duplication, use set the first, convert it to arraylist at last
    //subroutine that traverse the matrix and build words
    //stop traverse when maximum word length is reached, or done all possible traversal
    //make sure it does not going back to revisit a position
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        ArrayList<String> ans = new ArrayList<String>();
        //build trie tree to represent dictionary.
        // it's also doable to use set as dictionary because hash looking up is even faster
        Set<String> dict = new HashSet();
        for(String word : words){
            dict.add(word);
        }
        //starting from any position in matrix
        String curWord = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                search(board, i, j,curWord , ans,dict);
            }
        }
        return ans;
        // write your code here

    }
//valid steps to find neighbors
    //down,right,up,left
    public int [] rd = {1, 0, -1, 0};
    public int [] cd = {0, 1, 0, -1};
    private boolean isNotValidMove(char[][] board,int r,int c){
        return r < 0 || r >= board.length || c < 0 || c >= board[0].length||board[r][c]==0;
    }
    //r,y is next position to examine
    public void search(char[][] board, int r, int c, String curWord, ArrayList<String> ans, Set<String> dict) {
        //as long as valid move, continue. it will search all the words, include words that includes other shorter words
        if(dict.contains(curWord)&&!ans.contains(curWord)){
            ans.add(curWord);
        }
        if(isNotValidMove(board,r,c) )
            return ;

        char curChar = board[r][c];
        board[r][c] = 0;//mark,mark the board so remember visited cells in subsequent paths
        for(int i = 0; i < 4; i++){
            search(board, r+ rd[i], c+ cd[i], curWord+curChar, ans,dict);
        }
        //restore the cell value for other searchs
        board[r][c] = curChar;//back tracking
    }
    public static void main(String... args){
        WordSearchII_Set o = new WordSearchII_Set();
//        char[][] board = new char[][]{
//                {'d','o','a','f'},{'a','g','a','i'},{'d','c','a','n'}
//        };
//        String[] sa = new String[]{"dog","dad","dgdg","can","again"};
        char[][] board = new char[][]{
                {'a','b','c','e'},{'s','f','e','s'},{'a','d','e','e'}
        };
        String[] sa = new String[]{"abceseeefs","abceseedasfe"};
        ArrayList<String> words = new ArrayList<String>();
        words.addAll(Arrays.asList(sa));
        System.out.println(o.wordSearchII(board,words));
    }
}
