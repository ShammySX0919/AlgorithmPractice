package lintcode.ladder.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * median is middle value in a list. some times, for even numbers, it's defined as average of two middle values.
Clarification

 What's the definition of Median?
 - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
method 1:
 Brute force: keep an array, sort it, get a[(n-1)/2], where n is current number of elements
method 2:two priority queue, priority queue's time complexity is O(lgn)
 one minimum, one maximum, try to balance the two, median is the one with bigger size or maximum queue if size is same

 * Created by andrew on 22/11/16.
 */
public class DataStreamMedian {
    public int[] medianII(int[] nums) {
        int[] result = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            result[i] = getMedianBruteForce(nums,i);
        }
        return result;
    }

    /**
     * it lets me passed on this method.
     * @param nums
     * @param end
     * @return
     */
    private int getMedianBruteForce(int[] nums, int end){
        if(end==0)return nums[0];
        Arrays.sort(nums,0,end+1);//toindex is exclusive
        return nums[(end-1)/2];
    }
//better solution here
// when using two priority queues, thinking to add new element to max queue, then poll the max from it and
//then adding to min queue. that is to make every element go through the first queue the first.
    public PriorityQueue<Integer> minheap, maxheap;
    public int[] medianIIBetter(int[] nums) {
        maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
        minheap = new PriorityQueue<Integer>();

        int[] result = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            addNum(nums[i]);
            result[i] = findMedian2();
        }
        return result;
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        //maxheap--minheap
        //according to this vision, adding it to maxheap the first, then moving max of it to min queue
        //but if maxheap size is smaller than minheap, we will try to add it back
        maxheap.add(num);
        minheap.add(maxheap.poll());
        if (maxheap.size() < minheap.size()) {//rebalance the queues
            maxheap.add(minheap.poll());//try to make sure each element going through maxheap the first
        }
    }
    // Returns the median of current data stream
    public double findMedian1() {
        if (maxheap.size() == minheap.size()) {
            return (maxheap.peek() + minheap.peek()) * 0.5;
        } else {
            return maxheap.peek();
        }
    }

    /**
     * this problem picks the left value if there are even number of numbers
     * @return
     */
    public int findMedian2() {
        //if (maxheap.size() >= minheap.size()) {
            return  maxheap.peek();
        //}
        //else {//this branch is not reachable
          //  return minheap.peek();
        //}
    }
    public static void main(String[] args){
        int[] data = new int[]{1,2,3,4,5};
        DataStreamMedian o = new DataStreamMedian();
        System.out.println(o.medianIIBetter(data));
    }

}
