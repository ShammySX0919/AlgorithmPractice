package leetcode.easy;

/**
 * Created by andrew on 23/01/17.
 */
public class Easy492_ConstructRectangle {
    public int[] constructRectangle(int area) {
        int[] ans = new int[2];
        int w = (int)(Math.sqrt(area));//maximum W
        while(w>=1 &&area%w!=0){
            w--;
        }
        ans[1]=w;
        ans[0]=area/w;
        return ans;
    }
}
