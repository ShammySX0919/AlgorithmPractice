package amazon.medium;
//it's easier to just find out the maxium sum, just record max so far and max ending here
//the time the max ending here is negtive, is the time to exclude what has been inlucded---reset, because we are seeking for maximum sum
//any negative will make it less
public class LargestSumContiguousSubarray {
	//copied from geeksforgeeks
	public static void main (String[] args)
    {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " +
                                       maxSubArraySum(a));
    }
 
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
 
        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
