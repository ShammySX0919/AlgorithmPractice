package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 27/10/16.
 */
public class Medium442_FindAllDuplicatesInArray {
    public static List<Integer>  findDuplicates(int[] nums){
        List<Integer> ans = new ArrayList<Integer>();
        //we do not change abs value, so Math.abs(num) is original value
        //n-1 this is because array index is between 0 and n-1, use n-1 to make sure no overflow
        for(int num:nums){
           if( nums[Math.abs(num)-1]>0){
                nums[Math.abs(num)-1]=nums[Math.abs(num)-1]*(-1);//not changing absolute value
            }else{
               ans.add(Math.abs(num));
           }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(nums));

    }
}
