package dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BiggestConnectedRegion {

	// BFS
	public static int getBiggestRegionBFS(int[][] matrix) {
		int maxNum = 0;
		int row = matrix.length;
		int col = matrix[0].length;
		Set<String> regions = new HashSet<String>();

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				// avoid repeat region compuation
				if (matrix[r][c] == 1 && !regions.contains(r + ":" + c)) {
					Set<String> region = getRegion(matrix, r, c);
					// System.out.println("region size:"+region.size());
					maxNum = Math.max(maxNum, region.size());
					regions.addAll(region);
				}
			}
		}
		return maxNum;
	}

	// BFS
	private static Set<String> getRegion(int[][] matrix, int rCur, int cCur) {
		Set<String> region = new HashSet<String>();
		if (matrix[rCur][cCur] == 0)
			return region;
		Queue<String> q = new LinkedList<String>();
		q.add(rCur + ":" + cCur);
		while (!q.isEmpty()) {
			String coordination = q.poll();
			// avoid cycle
			if (region.contains(coordination)) {
				continue;
			}
			// otheerwise, process it by
			// 1. mark it as visited
			region.add(coordination);// mark it visited
			// 2. adding its neighbors for further process
			String[] rac = coordination.split(":");
			int r = Integer.valueOf(rac[0]);
			int c = Integer.valueOf(rac[1]);
			// add neighbors if neighbors are 1
			// up
			if (r > 0 && matrix[r - 1][c] == 1) {
				q.add((r - 1) + ":" + (c));
			}
			// up right
			if (r > 0 && c < matrix[0].length - 1 && matrix[r - 1][c + 1] == 1) {
				q.add((r - 1) + ":" + (c + 1));
			}
			// right
			if (c < matrix[0].length - 1 && matrix[r][c + 1] == 1) {
				q.add((r) + ":" + (c + 1));
			}
			// down right
			if (r < matrix.length - 1 && c < matrix[0].length - 1
					&& matrix[r + 1][c + 1] == 1) {
				q.add((r + 1) + ":" + (c + 1));
			}
			// down
			if (r < matrix.length - 1 && matrix[r + 1][c] == 1) {
				q.add((r + 1) + ":" + (c));
			}
			// down left
			if (r > 0 && c > 0 && matrix[r - 1][c - 1] == 1) {
				q.add((r - 1) + ":" + (c - 1));
			}
			// left
			if (c > 0 && matrix[r][c - 1] == 1) {
				q.add((r) + ":" + (c - 1));
			}
			// left up
			if (c > 0 && r > 0 && matrix[r - 1][c - 1] == 1) {
				q.add((r - 1) + ":" + (c - 1));
			}

		}
		return region;
	}

	// DFS, better
	public static int getRegionSizeDFS(int[][] matrix, int row, int col) {
		// do boundary check here to avoid hassle in each of call later
		if (row < 0 || col < 0 || row >= matrix.length
				|| col >= matrix[0].length) {
			return 0;
		}
		// only 1 values are interested
		if (matrix[row][col] == 0) {
			return 0;
		}
		matrix[row][col] = 0;// mark it as 0, serving dual functions:
		// 1. mark as visited in this dfs
		// 2. mark as visited in calling function to avoid repeat compuation
		int size = 1;// we marked one above
		// for adjacent neighbors
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = col - 1; c <= col + 1; c++) {
				if (r != row || c != col) {
					size += getRegionSizeDFS(matrix, r, c);
				}
			}
		}
		return size;
	}

	public static int getBiggestRegionDFS(int[][] matrix) {
		int maxRegion = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				// we set matrix[][]=0 while DFS so that here to avoid repeat
				if (matrix[row][col] == 1) {
					int size = getRegionSizeDFS(matrix, row, col);
					maxRegion = Math.max(maxRegion, size);
				}
			}
		}
		return maxRegion;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int grid[][] = new int[n][m];
		for (int grid_i = 0; grid_i < n; grid_i++) {
			for (int grid_j = 0; grid_j < m; grid_j++) {
				grid[grid_i][grid_j] = in.nextInt();
			}
		}
		// System.out.println(getBiggestRegionBFS(grid));
		System.out.println(getBiggestRegionDFS(grid));
	}
}