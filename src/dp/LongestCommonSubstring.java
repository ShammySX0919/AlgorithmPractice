package dp;

/**
 * O(m*n)
 * Created by 212595974 on 5/18/2017.
 */
public class LongestCommonSubstring {
    public int longestCommonSubstring(String A, String B) {
        // state: f[i][j] is the length of the common substring before this position, including this position
        //f[][] is 1 based, string.charAt is 0 based
        // ended with A[i - 1] & B[j - 1] in A[0..i-1] & B[0..j-1]
        int n = A.length();
        int m = B.length();
        int[][] f = new int[n + 1][m + 1];//it's based on previous statistics, so make extra for [0][0]=0,[0][x]=0,[x][0]=0

        // initialize: f[i][j] is 0 by default

        // function: f[i][j] = f[i - 1][j - 1] + 1 or 0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    //meaning characters in two string at i in first str and j in second str are different, so reset counter for that position
                    f[i][j] = 0;
                }
            }
        }

        // answer: max{f[i][j]}
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                max = Math.max(max, f[i][j]);
            }
        }

        return max;
    }
public static void main(String[] args){
    LongestCommonSubstring o = new LongestCommonSubstring();
    System.out.println(3==o.longestCommonSubstring("abcdef","abxxxxdef"));
}
}
