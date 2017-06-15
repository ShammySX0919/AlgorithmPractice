package amazon.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubArrayWithGivenSum {
	boolean subArraySumBF(int arr[], int n, int sum) 
    {
        int curr_sum, i, j;
 
        // Pick a starting point
        for (i = 0; i < n; i++) 
        {
            curr_sum = arr[i];
 
            // try all subarrays starting with 'i'
            for (j = i + 1; j <= n; j++) 
            {
                if (curr_sum == sum) 
                {
                    int p = j - 1;
                    System.out.println("Sum found between indexes " + i
                            + " and " + p);
                    return true;
                }
                if (curr_sum > sum || j == n)
                    break;
                curr_sum = curr_sum + arr[j];
            }
        }
 
        System.out.println("No subarray found");
        return false;
    }
	//optimization. not allow negative integer also
	static int[] findSubarrayOfSum(int[] arr, int sum){
		int curr_sum = arr[0], start = 0, i;
		 
        // Pick a starting point
        for (i = 1; i <= arr.length; i++) 
        {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (curr_sum > sum && start < i-1)//the current i has not been added yet, so stop up to i-1's previous
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }//element prev of start has been removed
            
            // If curr_sum becomes equal to sum, then return true. check it after any changes 
            if (curr_sum == sum) 
            {
                int t = i-1;//the current i has not been added yet
                int res[] = new int[2];
                res[0]=start;res[1]=t;
                return res;
            }
             
            // Add this element to curr_sum
            if (i < arr.length)
            curr_sum = curr_sum + arr[i];
             
        }
 
        
		return null;
	}
	//this is a solution to remember
	//running sum is accumulation of all previous elements, diff between it and k, if exists in map(valid rSum), then a range is found
	static int[] findSubarrayOfSumWithNegtive(int[] arr, int k){
		int curr_sum =0;
		Map<Integer,Integer> rSums = new HashMap<>();
		int[] res = new int[2];
        // Pick a starting point
        for (int i = 0; i < arr.length; i++) 
        {
        	curr_sum +=arr[i];
        	if(curr_sum==k){
        		res[0]=0;res[1] = i;
        		return res;
        	}
        	if(rSums.containsKey(curr_sum-k)){//that means diff betwee rSums.get(curr_sum-k) and i is an anser
        		res[0] = rSums.get(curr_sum-k)+1;//the k does not include rSum.get(curr_sm-k)
        		res[1] = i;
        		return res;
        	}
        	rSums.put(curr_sum,i);
        }	
        
		return null;
	}
	public static void main(String[] args){
		int arr[] = new int[]{5,6,11,4};
		int[] res = findSubarrayOfSumWithNegtive(arr,17);
		System.out.printf("%d-%d",res[0],res[1]);
		res = findSubarrayOfSumWithNegtive(arr,5);
		if(res!=null)
		System.out.printf("%d-%d",res[0],res[1]);
		res = findSubarrayOfSumWithNegtive(arr,15);
		System.out.printf("%d-%d",res[0],res[1]);
	}
}
