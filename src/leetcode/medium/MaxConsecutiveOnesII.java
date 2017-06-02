package leetcode.medium;

/**
 * LeetCode 487. Max Consecutive Ones II
 * <p>
 * Given a binary array, preorderFind the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * <p>
 * Note:
 * <p>
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * <p>
 * Analysis:
 * Translate this to: maximum of consecutive section that contains k 0s
 * The idea is to keep a window [l, h] that contains at most k zero. K in this case is 1
 * Created by Andrew Ma on 1/17/2017.
 */
public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, zerosInWindow = 0, k = 1; // flip at most k zero
        //l low bound, h high bound of a window
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                zerosInWindow++;//number of zeros in windows
            }
            //keep zeros in the window by moving lower boundary up
            //meeting another 0, move l forward
            while (zerosInWindow > k) {
                //if (nums[l++] == 0) {
                if (nums[l] == 0) {
                    zerosInWindow--;
                }
                l++;//moving low bound up
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}
