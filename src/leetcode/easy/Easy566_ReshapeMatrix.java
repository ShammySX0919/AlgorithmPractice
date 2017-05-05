package leetcode.easy;

public class Easy566_ReshapeMatrix {

	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if ((nums.length * nums[0].length) != (r * c))
			return nums;
		int res[][] = new int[r][c];
		int r2 = 0, c2 = 0;
		for (int r1 = 0; r1 < nums.length; r1++) {
			for (int c1 = 0; c1 < nums[0].length; c1++) {
				// this is row order of nums
				r2 = (r1 * nums[0].length + c1) / c;
				c2 = (r1 * nums[0].length + c1) % c;
				res[r2][c2] = nums[r1][c1];
			}
		}
		return res;
	}
}