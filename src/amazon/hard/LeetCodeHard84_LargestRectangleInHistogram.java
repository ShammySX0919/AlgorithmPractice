package amazon.hard;

import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * Created by andrew on 6/6/2017.
 */
public class LeetCodeHard84_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            //at last, putting 0 that is shorter than any bar, to trigger a last calculation
            int h = (i == len ? 0 : heights[i]);//current bar height
            if(s.isEmpty() || h >= heights[s.peek()]){
                s.push(i);//push bar index to stack if it is higher than last height in stack
            }else{
                //if current bar is shorter
                int tp = s.pop();//pop out last unprocessed from stack
                //current bar is i, the tp's index is i-1,and tp's previous index is s.peek()
                //so bar of tp can expand i-1-s.peek() width
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;//keep i the same in next loop, to compare bar at i with new last bar in stack
            }
        }
        return maxArea;
    }
    //brute force, for each bar, expand left and right until it reaches a bar that is shorter than it,
    // or reaching to the start or end. area based on current bar is maximum expanded width*heights[i]
    public static int largestRectangleAreaBF(int[] heights) {
        int len = heights.length;

        int maxArea = 0;
        int l,r;
        //for each bar, caculate its maximum area based on its height
        //O(n^2)
        for(int i = 0; i < len; i++) {
            l = i;
            r = i;
            //move left pointer until meeting a height shorter than current bar
            while (l >= 0 && heights[l] >= heights[i]) l--;
            l++;
            while (r < len && heights[r] >= heights[i]) r++;
            r--;
            maxArea = Math.max(maxArea, (r - l + 1) * heights[i]);
        }
        return maxArea;
    }
    public static void main(String[] args){
        int heights[] = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
        System.out.println(largestRectangleAreaBF(heights));
    }
}
