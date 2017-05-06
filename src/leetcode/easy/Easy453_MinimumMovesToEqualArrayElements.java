package leetcode.easy;

public class Easy453_MinimumMovesToEqualArrayElements {
	 public int minMoves(int[] nums) {
	        int min=nums[0],sum=0;
	        for(int i=0;i<nums.length;i++){
	            sum+=nums[i];
	            if(min>nums[i])
	                {min=nums[i];}
	        }
	        return sum-min*nums.length;
	    }
}
