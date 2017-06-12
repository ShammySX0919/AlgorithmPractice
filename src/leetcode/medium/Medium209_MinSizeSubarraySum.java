package leetcode.medium;

/**
 * Created by andrew on 6/11/2017.
 */
public class Medium209_MinSizeSubarraySum {
    int minSubArrayLen(int s, int[] nums)
    {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        //right pointer is running pointer
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= s) {
                if(sum==s) {
                    ans = Math.min(ans, right - left +1);
                }
                sum -= nums[left++];
            }
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;
    }
}
