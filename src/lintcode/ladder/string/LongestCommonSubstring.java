package lintcode.ladder.string;

public class LongestCommonSubstring {
	 /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if(A==null||B==null||A.length()==0||B.length()==0){
            return 0;
        }
        //this could be considered as dp: dp[r][c] = 0; if A.charAt(r)!=B.charAt(c);
        //										   = dp[r-1][c-1]+1; if A.charAt(r)!=B.charAt(c);
        /*b b b b b b
        a
        a
        a
        a
        
        */
        int[][] matches = new int[A.length()][B.length()];//initialized with 0s
        int maxLen=0;//update maxLen while new matches was found
        //O(m*n)
        for(int r=0;r<A.length();r++){
            for(int c=0;c<B.length();c++){
                if( (A.charAt(r) == B.charAt(c))){
                    if(r-1>=0 && c-1>=0 && A.charAt(r-1)==B.charAt(c-1)){
                        matches[r][c] = matches[r-1][c-1]+1;
                    }else
                    {
                        matches[r][c] = 1;
                    }
                    maxLen = Math.max(maxLen,matches[r][c]);
                }
            }
        }
        
        //if using boolean, I will have to recount nodes on diagonal line
        return maxLen;
    }
}
/*


Given two strings, find the longest common substring.

Return the length of it.

Notice

The characters in substring should occur continuously in original string. This is different with subsequence.

Example

Given A = "ABCD", B = "CBCE", return 2.

*/