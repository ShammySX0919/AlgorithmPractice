package leetcode.contest.week35;

public class Easy605_CanPlanFlower {
	public boolean canPlanFlower(int[] flowerbed, int n) {
		int count = 0;
		//key is to tream -1 and length element as value of 0
		for (int i = 0; i < flowerbed.length && count < n; i++) {
			if (flowerbed[i] == 0) {
				// get next and prev flower bed slot values. If i lies at the
				// ends the next and prev are considered as 0.
				int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
				int prev = (i == 0) ? 0 : flowerbed[i - 1];
				if (next == 0 && prev == 0) {
					flowerbed[i] = 1;//I did this too!
					count++;
				}
			}
		}

		return count >= n;
	}
}