package lintcode.ladder.binarysearch;

public class SearchIn2DMatrix {

	/**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0)return false;
        // write your code here
        int line=0;
	int len=matrix.length-1;
	int col=matrix[0].length-1;
	while (line<=len && col>=0){
		int num= matrix[line][col];
		if (num>target) col--;
		if (num<target) line++;
		if (num==target) return true;}
	
    return false;
    }
}
//shit, not using binary search but passed. this is actually O(m+n)
//another thought:
//two function, first one locate row using binary search
//second one search that row with binary search
/*


Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

Have you met this question in a real interview?
Example

Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]

Given target = 3, return true.

*/