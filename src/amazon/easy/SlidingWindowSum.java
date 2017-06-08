package amazon.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 6/7/2017.
 */
public class SlidingWindowSum {
    public static List<Long> getWindowSum(int[] a, int windowSize){
        List<Long> res = new ArrayList<>();
        int i=0;
        long sum = 0;
        //calculate first range sum. i is 0 based, so stop at windowSize-1 that is windowSize number of elements
        for(;i<windowSize;i++){
                 sum+=a[i];
        }
        res.add(sum);//adding first result
        //if windows size is bigger than array size, then return result
        if(windowSize>=a.length){
            return res;
        }
        //continue i until it reaches end of array
        //i is pointing to next element after first loop
        for(;i<a.length;i++){
            //for each new position, left most element should be removed out of sum
            sum-=a[i-windowSize];
            sum+=a[i];
            res.add(sum);
        }
        return res;
    }
    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4,5};
        System.out.println(getWindowSum(a,2));
    }
}
