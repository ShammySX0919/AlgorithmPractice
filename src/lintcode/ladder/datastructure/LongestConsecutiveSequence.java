package lintcode.ladder.datastructure;

import java.util.HashMap;

public class LongestConsecutiveSequence {
	/**
     * @param nums: A list of integers
     * @return an integer
     */
    public int longestConsecutive(int[] num) {
        // write you code here
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);
                
                // keep track of the max length 
                res = Math.max(res, sum);
                
                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                //while(left>0 &&map.get(n-left)!=null){
                map.put(n - left, sum);
                  //  left--;
                //}
               // while(right>0&&map.get(n+right)!=null){
                    map.put(n + right, sum);
                 //   right--;
                //}
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }
}
