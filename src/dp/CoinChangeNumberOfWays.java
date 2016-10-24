package dp;

import java.util.Scanner;

public class CoinChangeNumberOfWays {
	public static long makeChange(int[] coins, int money) {
		long dp[][] = new long[coins.length + 1][money + 1];
		// 0 denom
		for (int i = 0; i < money + 1; i++) {
			dp[0][i] = 0;// no solution for each money value
		}
		// 0 money value
		for (int i = 0; i < coins.length + 1; i++) {
			dp[i][0] = 0;// no coin can do 0 change
		}
		for (int c = 1; c < coins.length + 1; c++) {
			for (int m = 1; m < money + 1; m++) {
				if (m < coins[c - 1]) {
					// no change at this, copy the last denom's accumulated
					// solutions
					dp[c][m] = dp[c - 1][m];
				} else if (m == coins[c - 1]) {
					// we get one more solution
					dp[c][m] = dp[c - 1][m] + 1;
				} else // if (m>coins[c-1])
				{
					// then it is last denom's accumulated solution + lesser
					// value's accumulated solution
					dp[c][m] = dp[c - 1][m] + dp[c][m - coins[c - 1]];
				}
			}
		}
		return dp[coins.length][money];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int coins[] = new int[m];
		for (int coins_i = 0; coins_i < m; coins_i++) {
			coins[coins_i] = in.nextInt();
		}
		System.out.println(makeChange(coins, n));
	}
}