package dp;

import java.util.Scanner;

public class ClimStairs {
	static void numWays(int n){
        //let dp[i] is num ways to reach stair i. 
        //each dp[i] is an accumulation of ways to reach to there
        int dp[] = new int[n+1];
        //assuming you are at nth stair, to reach there
        //you can step 1 from n-1th stair
        //or step 2 from n-2th stair or
        //step 3 from n-3th stair
        //so you have that many ways to reach to nth stair. 
        //adding them up is the ways to reach to nth stair
        //dp[3]=dp[2]+dp[1]+dp[0];
        
        /*
        to reach to stair 1, you have one way:dp[1]=1=dp[0]
        to reach to stair 2, you have: 1 from dp[0], that is 1 way,2. from dp[1], that's 1 way.
        to reach to stair 3, you can : 1. dp[0]->dp[3],2.dp[2]->dp[3],3. dp[1]->dp[3]
        whatever you use to reach dp[2] now contributes to dp[3]
        */
        if(n==1){
            System.out.println(1);
        }else if(n==2){
            System.out.println(2);
        }else{
            dp[0]=1;//0 is base 
            dp[1]=1;dp[2]=dp[1]+dp[0];

            for(int i=3;i<=n;i++){
                dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
            }
            System.out.println(dp[n]);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
            numWays(n);
        }
        
    }
}
