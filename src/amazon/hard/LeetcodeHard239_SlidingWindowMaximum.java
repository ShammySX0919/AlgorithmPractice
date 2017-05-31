package amazon.hard;

import java.util.*;

/**
 * Created by andrew on 5/30/2017.
 */
public class LeetcodeHard239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];
        int rIndex = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);//put to tail
            if (i >= k - 1) {
                r[rIndex++] = a[q.peek()];
            }
        }
        return r;
    }
    //non linear, helped with priority queue, easy to understand
    public int[] maxSlidingWindowNonLinear(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];
        if(nums.length == 0) return new int[0];
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return Integer.compare(i2, i1);
            }
        });
        //putting first k to max priority queue
        for(int i = 0; i < k; i ++){
            queue.add(nums[i]);
        }
        //get first result
        result[0] = queue.peek();
        //for each subsequent element,
        for(int i = k; i < len; i ++){
            //removing the one just slided out of window
            queue.remove(nums[i - k]);
            //adding new one
            queue.add(nums[i]);
            //get corresponding position max
            result[i - k + 1] = queue.peek();
        }

        return result;
    }
}
