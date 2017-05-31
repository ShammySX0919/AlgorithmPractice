package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * preorderFind the length of shortest transformation sequence from beginWord to endWord,
 * such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.

 Analysis: this is BFS for shortest path. it removes word from dict to realize visited set that is usually used
 * Created by andrew on 5/22/2017.
 */
public class Medium127_WordLadder {
    /**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return an integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        Queue<String> queue = new LinkedList<String>();
        if(start.equals(end))return 1;
        queue.offer(start);
        dict.remove(start);//this is to avoid repeat access
        int length = 1;//make it 1 because it treated start word as one of sequence according to problem description.

        //BFS level traverse
        while (!queue.isEmpty()) {
            int count = queue.size();
            //this is a good way to access queue level by level
            //for all elements in current level
            for (int i = 0; i < count; i++){
                //get current word
                String current = queue.poll();
                //for each character in word
                for (int j = 0; j < current.length(); j++) {
                    //change it to another 25 characters to make a new word
                    for (char c = 'a'; c <= 'z'; c++) {
                        //ignore same character
                        if (c == current.charAt(j)) {
                            continue;
                        }
                        //make new word by replacing it with different character
                        String tmp = replace(current, j, c);
                        //only process when word is in dict
                        if (dict.contains(tmp)){
                            //if preorderFind end word, return length
                            if (tmp.equals(end)) {
                                return length + 1;
                            }
                        //if it is dict word, put it to queue for further process
                        //and remove it from dict to avoid repeat process

                            queue.offer(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            //one layer is done, increasing path length
            length++;
        }

        return 0;
    }

    /**
     * change character at index to c in string
     * @param s
     * @param index
     * @param c
     * @return
     */
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}
