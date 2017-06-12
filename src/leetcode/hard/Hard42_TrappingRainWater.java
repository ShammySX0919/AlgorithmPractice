package leetcode.hard;

/**
 * For each element in the array, we find the maximum level of water it can trap after the rain,
 * which is equal to the minimum of maximum height of bars on both the sides minus its own height
 * Created by andrew on 6/11/2017.
 */
public class Hard42_TrappingRainWater {
    static int trap(int[] height)
    {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            //Note: even though these two loops check max_left the entire left side, and max_right the entire right side,
            //it only figures out how much water this bar contributes to total trapped water.
            //it is based on how high the water on the current bar could be
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }
    //figure out max_left and max_right in advance, so no need to calculate them again and again in each loop
    //I like this solution better, more natural thought and code organization
    static int trapDP(int[] height)
    {
        if(height==null||height.length<3)return 0;
        int ans = 0;
        int size = height.length;
        int[] max_left = new int[size];//left height limit at i
        int[] max_right = new int[size];//right height limit at i
        max_left[0] = height[0];
        for(int i=1;i<size;i++)
            max_left[i] = Math.max(max_left[i-1],height[i]);
        max_right[size-1] = height[size-1];
        for (int i = size-2; i >= 0; i--)
            max_right[i] = Math.max(max_right[i+1],height[i]);
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(max_left[i], max_right[i]) - height[i];
        }
        return ans;
    }
    public static void main(String[] args){
        //int[] traps = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int[] traps = new int[]{0,1,0,2,1,0,1,1,2,1,3,1};
        System.out.println(trap(traps));
        System.out.println(trapDP(traps));
    }
}
