package leetcode.medium;

public class Medium560_SubarraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
        long runningTotal[] = new long[nums.length+1];
        //give it an extra element, so that same calculation can also be applied to nums[0]
        runningTotal[0] = 0;
        //calculate running total, r[1] is for nums[0]
        for(int i=0;i<nums.length;i++){
            runningTotal[i+1] = runningTotal[i]+nums[i]; 
        }
        int res = 0;
        for(int i=0;i<runningTotal.length-1;i++){
            for(int j=i+1;j<runningTotal.length;j++){
                //this could be used because r[0] is put there fornum[0]
                //nums[0] itself can be one answer. with r[0], we can always use a-b as a fomular to do the calculation
                if(runningTotal[j] - runningTotal[i]==k)res++;
            }
        }
        return res;
    }
	public static void main(String[] args){
		int test[]={1,2,3,1,1,6,7,8,9};
		System.out.println(Medium560_SubarraySumEqualsK.subarraySum(test,2));
	}
}