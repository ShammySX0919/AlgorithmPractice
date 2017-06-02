package lintcode.ladder.datastructure;

import java.util.Stack;

/**
 * Created by andrew on 5/27/2017.
 */
public class LargestRecrangleAreaInHistgram {
    /**
     * @param height: A list of integer. its width is 1
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        // write your code here
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        int i = 0;

        while (i < height.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                int p = stack.pop();
                int h = height[p];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(h * w, max);
            }

        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int h = height[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(h * w, max);
        }

        return max;
    }
    public static void main(String[] args){
        LargestRecrangleAreaInHistgram o = new LargestRecrangleAreaInHistgram();
        int[] data = new int[]{2,1,5,6,2,3};
        System.out.println(o.largestRectangleArea(data));
        data = new int[]{1,2,3,6,2,3};
        System.out.println(o.largestRectangleArea(data));
    }
}
