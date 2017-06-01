package amazon.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by 212595974 on 5/31/2017.
 */
public class LeetCode215_KthLargestInArray {
    //by sorting, O(nlgn)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    //O(N lg K) running time + O(K) memory with priority queue
    public int findKthLargestPQ(int[] nums, int k) {
        //min queue
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                //pull out smallest, there are still k element greater than it
                pq.poll();
            }
        }
        //peek the smallest, that is kth largest
        return pq.peek();
    }
    //there is an O(n)
    //that is to do shuffle and then quick search
    //to do shuffle, get a randome index and then swap with i. even i is sequentially, it ends up as random sort.
}
