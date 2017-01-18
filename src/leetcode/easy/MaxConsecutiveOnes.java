package leetcode.easy;

/**
 * Created by Andrew Ma on 1/17/2017.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int cnt = 0;
        for (int i : nums) {
            if (i == 0) {
                ans = Math.max(ans, cnt);
                cnt = 0;
            } else {
                cnt++;
            }
        }
        ans = Math.max(ans, cnt);
        return ans;
    }
}
