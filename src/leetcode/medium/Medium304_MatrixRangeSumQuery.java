package leetcode.medium;
/**
 * because it needs to query many times, so the brute force sum for every query is not favorable.
 * usually this kind of problem could be optimized by preprocess to have a running sum at each cell,
 * then using a formula to calculate the result out.
 * 
 * hashmap could be used for look for candidates if they could not be figured out by pointers. such as
 * in subarray sum of k
 * 
 * @author Andrew
 *
 */
public class Medium304_MatrixRangeSumQuery {
	int[][] sumRegion;
	public  Medium304_MatrixRangeSumQuery(int[][] matrix) {
	    if (matrix.length != 0)  sumRegion = new int[matrix.length + 1][matrix[0].length + 1];
	    
	    for (int i = 0; i < matrix.length; i++) {
	        int sum = 0;//key: every new row, reset sum. 
	        //sumRegion[i][j + 1] from above cell is accumulation up to that col in above row
	        for (int j = 0; j < matrix[0].length; j++) {
	            sum += matrix[i][j];
	            sumRegion[i + 1][j + 1] = sum + sumRegion[i][j + 1]; 
	        }
	    }
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		//sumRegion[row1][col1] was included in both sumRegion[row1][col2 + 1] and sumRegion[row2 + 1][col1]
	    return sumRegion[row2 + 1][col2 + 1] - sumRegion[row1][col2 + 1] - sumRegion[row2 + 1][col1] + sumRegion[row1][col1];
	}
}
