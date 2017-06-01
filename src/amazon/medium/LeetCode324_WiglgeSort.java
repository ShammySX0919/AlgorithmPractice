package amazon.medium;

import java.util.Arrays;

/**
 *
 * Created by 212595974 on 5/31/2017.
 */
public class LeetCode324_WiglgeSort {
    /**
     * this is not optimized, but easy to understand:
     * 1. sort array so that I know which is bigger than which
     * 2. find the middle point and try to make a new array by picking small from small side and big from bigger side
     *    small and big side is separated by middle point
     *    middle point is depending on number of elements in array
     * 3. my dummy ways is to reorganize them into a new array, and then copy back to original array
     * @param nums
     */
    public static void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) return;
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        int mid = nums.length%2==0?nums.length/2-1:nums.length/2;
        int s=mid,l=nums.length-1;
        int i=0;
        while(s>=0 &&l>mid){
            res[i++] = nums[s--];
            if(i<nums.length){
                res[i++] = nums[l--];
            }
        }
        //for odd number of array, left side misses one
        if(res[nums.length-1]==0){
            res[nums.length-1] = nums[0];
        }
        for(i=0;i<nums.length;i++){
            nums[i] = res[i];
        }
    }
    public static void main(String... args){
        int[] a = new int[]{1,3,2,2,3,1};
        wiggleSort(a);
        System.out.println(a);
    }
}
