package leetcode.contest.week36;

import java.util.Arrays;

/**
 * https://www.wyzant.com/resources/lessons/math/geometry/triangles/inequalities_and_relationships
 * condition: sum of any two side is greater than third one.
 * Created by andrew on 6/10/2017.
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ret = 0;
        for(int i = 0;i < nums.length;i++){
            int k = 0;
            for(int j = i+1;j < nums.length;j++){
                while(k < i && nums[k] + nums[i]<= nums[j])k++;
                ret += i-k;
            }
        }
        return ret;
    }
//....k...i....j......
// as long as k = i>j, since it is sorted array, it guaranteed i+j>k and k+j>i since j is greater than k and i
// so we only need to make sure k+i>j
}
/*
611. Valid Triangle Number

    User Accepted: 0
    User Tried: 0
    Total Accepted: 0
    Total Submissions: 0
    Difficulty: Medium

Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:

Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

Note:

    The length of the given array won't exceed 1000.
    The integers in the given array are in the range of [0, 1000].

 */