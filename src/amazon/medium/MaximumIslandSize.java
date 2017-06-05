package amazon.medium;

public class MaximumIslandSize {
	/**
	 * 0 is ocean and 1 is island, figure out maximum island size
	 * 
	 * @param matrix
	 * @return
	 */
	public int maxIslandSize(int[][] matrix) {
		int ROW = matrix.length, COL = matrix[0].length;
		boolean[][] visited = new boolean[ROW][COL];// by default it is false
		int maxSize = 0;
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COL; c++) {
				if (!visited[r][c] && matrix[r][c] == 1) {
					maxSize = Math.max(maxSize, dfs(matrix, visited, r, c));
				}
			}
		}
		return maxSize;
	}

	/**
	 * use dfs to traverse all connected 1s. not using bfs is because it
	 * requires special data structure for (r,c)
	 * 
	 * @param matrix
	 * @param visited
	 * @param r
	 * @param c
	 * @return
	 */
	private int dfs(int[][] matrix, boolean[][] visited, int r, int c) {
		// These arrays are used to get row and column numbers
		// of 8 neighbors of a given cell
		// left-down,down,right-down,left,right,left-up,up,up-right
		int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
		
		int size = 0;
		// Mark this cell as visited
		if (validMove(matrix, visited, r, c)) {
			visited[r][c] = true;
			size = 1;
			for (int i = 0; i < 8; i++) {
				size += dfs(matrix, visited, r + rowNbr[i], c + colNbr[i]);
			}
		}
		return size;
	}

	private boolean validMove(int[][] matrix, boolean[][] visited, int r, int c) {
		// check index boundary
		if (r < 0 || r >= visited.length || c < 0 || c >= visited[0].length)
			return false;
		return !visited[r][c] && matrix[r][c] == 1;
	}

	public static void main(String[] args) throws java.lang.Exception {
		int M[][] = new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
				{ 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
		MaximumIslandSize o = new MaximumIslandSize();
		System.out.println(8 == o.maxIslandSize(M));
		System.out.println("max islands sze is: " + o.maxIslandSize(M));
	}
}
