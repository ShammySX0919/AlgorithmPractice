package lintcode.ladder.binarysearch;

public class FirstPositionOfTarget {
	/**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        //write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end) {//use start+1<end or start<end to avoid deadloop caused by start<=end
            mid = start + (end - start) / 2; // avoid overflow when (end + start)
            if (target < nums[mid]) {
                end = mid-1;
            } else if (target > nums[mid]) {
                start = mid+1;
            } else {
                end = mid;//equal
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        return -1;
    
    }
}
/*


For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.

If the target number does not exist in the array, return -1.
Have you met this question in a real interview?
Example

If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.

*/