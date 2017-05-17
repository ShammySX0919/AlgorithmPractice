package amazon.oa.y2017;

/**
 * leetcode 63
 * Follow up for "Unique Paths":

 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 For example,
 There is one obstacle in the middle of a 3x3 grid as illustrated below.

 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 The total number of unique paths is 2.

 Note: m and n will be at most 100.
 * Created by andrew on 5/16/2017.
 */
public class UniquePath2 {
    public static int numberUniquePath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int rs = matrix.length, cs = matrix[0].length;
        int[][] dp = new int[rs+1][cs+1];
        dp[rs - 1][cs] = 1;//please check the illustration in uniquePath.png to understand why this is set so
        for (int r = rs - 1; r >= 0; r--){
            for (int c = cs - 1; c >= 0; c--) {
                dp[r][c] = matrix[r][c] == 1 ? 0 : dp[r + 1][c] + dp[r][c + 1];
            }
        }
        return dp[0][0];
    }
    public static void main(String... args){
        int[][] values = new int[][]{
                {0,0,0},
        {0,1,0},
        {0,0,0}
        };
        System.out.println(numberUniquePath(values));
    }
}
