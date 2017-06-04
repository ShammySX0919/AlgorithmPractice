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
/**
605. Can Place Flowers

User Accepted: 0
User Tried: 0
Total Accepted: 0
Total Submissions: 0
Difficulty: Easy

Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: True

Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

Note:

The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.

*/