package amazon.easy;

import java.util.Arrays;

//sort the two arrays, then find the common elements
public class LeetCode350_IntersectionOfTwoArrays {
	
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int intersect[] = new int[Math.min(nums1.length, nums2.length)];
		int i = 0, j = 0, k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j])
				i++;
			else if (nums1[i] > nums2[j])
				j++;
			else {
				intersect[k++] = nums1[i];
				i++;
				j++;
			}
		}
		return Arrays.copyOf(intersect, k);

	}
}
