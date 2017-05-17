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
    public static int getUniquePath(int[][] matrix) {
        if(matrix.length==0)return 0;

        int rs = matrix.length, cs = matrix[0].length;
        int[][] paths = new int[rs+1][cs+1];
        paths[rs-1][cs] = 1;

        for (int r = rs - 1; r >= 0; r--){
            for (int c = cs - 1; c >= 0; c--) {
                //if blocked, then no path
                if (matrix[r][c] == 1) paths[r][c] = 0;
                else {
                    paths[r][c] = paths[r][c + 1] + paths[r + 1][c];
                }
            }
        }
        return paths[0][0];
    }
    public static int getUniquePath1(int rs,int cs) {

        int[][] paths = new int[rs+1][cs+1];
        paths[rs-1][cs] = 1;

        for (int r = rs - 1; r >= 0; r--){
            for (int c = cs - 1; c >= 0; c--) {

                    paths[r][c] = paths[r][c + 1] + paths[r + 1][c];

            }
        }
        return paths[0][0];
    }
    public static void main(String[] args){
        int[][] mat = new int[][]{
            {0,0,0},
            {0,1,0},
            {0,0,0}
            };
     System.out.println(getUniquePath(mat));
    }
}
