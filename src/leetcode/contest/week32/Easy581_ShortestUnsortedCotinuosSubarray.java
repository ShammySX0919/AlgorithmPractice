package leetcode.contest.week32;

import java.util.Arrays;

public class Easy581_ShortestUnsortedCotinuosSubarray {
	public int findUnsortedSubarray(int[] nums) {
        if(nums==null||nums.length==0||nums.length==1)return 0;
        int copy[] = new int[nums.length];
        System.arraycopy(nums,0,copy,0,nums.length);
        Arrays.sort(copy);
        int start=-1,end=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=copy[i]){
                start=i;
                break;
            }
        }
        //both are sorted
        if(start==-1)return 0;
        
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]!=copy[i]){
                end=i;
                break;
            }
        }
        return end-start+1;
    }
	//more concise
	public int findUnsortedSubarray2(int[] nums) {
        if(nums==null||nums.length==0||nums.length==1)return 0;
        int copy[] = new int[nums.length];
        System.arraycopy(nums,0,copy,0,nums.length);
        Arrays.sort(copy);
        int start=-1,end=nums.length-1;
        while(start<nums.length&&nums[start]==copy[start]){
            start++;
        }
       
        while(end>=0 &&nums[end]==copy[end]){
            end--;
        }
        return end-start+1;
    }
	//better smart algorithm
	public int findUnsortedSubarray3(int[] nums) {
		int n = nums.length, beg = -1, end = -2, min = nums[n-1], max = nums[0];
	    for (int i=1;i<nums.length;i++) {
	      max = Math.max(max, nums[i]);
	      min = Math.min(min, nums[n-1-i]);
	      if (nums[i] < max) end = i;
	      if (nums[n-1-i] > min) beg = n-1-i; 
	    }
	    return end - beg + 1;
    }
	public static void main(String... arg){
		int[] a = new int[]{2,6,4,8,10,9,15};
		//a = new int[]{1,3,2,2,2};
		//a = new int[]{1,2,3,3,3};
		Easy581_ShortestUnsortedCotinuosSubarray o = new Easy581_ShortestUnsortedCotinuosSubarray();
		System.out.println(o.findUnsortedSubarray3(a));
	}
}
