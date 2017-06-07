package amazon.hard;

import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
 * If we calculate such area for every bar ‘x’ and find the maximum of all areas, our task is done.
 * How to calculate area with ‘x’ as smallest bar? We need to know index of the first smaller (smaller than ‘x’) bar
 * on left of ‘x’ and index of first smaller bar on right of ‘x’.
 * Let us call these indexes as ‘left index’ and ‘right index’ respectively.
 * --->brute force is following this idea, but expensive way to figure out left index and right index
 * We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack once.
 * A bar is popped from stack when a bar of smaller height is seen.
 * When a bar is popped, we calculate the area with the popped bar as smallest bar.
 *
 * How do we get left and right indexes of the popped bar
 * – the current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’.
 * -this holds well when equal height bars are together. a bigger area will be calculated out in next loop
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
