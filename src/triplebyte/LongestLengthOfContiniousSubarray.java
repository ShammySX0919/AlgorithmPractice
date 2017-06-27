package triplebyte;

/**
 * Created by andrew on 6/27/2017.
 */
public class LongestLengthOfContiniousSubarray {
    int getLongestLengthContiniousSubarray(int[] arr){
        int[] dp = new int[arr.length];
        dp[0]=1;//start point is 1
        int ans=1;//minimum is 1. make this in-synch with dp array's current statistics
        for(int i=1;i<arr.length;i++){
            if(arr[i]>=arr[i-1]){
                    dp[i]=dp[i-1]+1;
                    ans = Math.max(ans,dp[i]);
            }
            else dp[i]=1; //treat it as a new start point.
        }
        return ans;
    }
    public static void main(String[] args){
        LongestLengthOfContiniousSubarray o = new LongestLengthOfContiniousSubarray();
        System.out.println(o.getLongestLengthContiniousSubarray(new int[]{1,2,3,3,2,4,6,7}));
    }
}
