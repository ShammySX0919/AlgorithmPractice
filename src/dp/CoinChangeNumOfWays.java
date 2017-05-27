package dp;

import java.util.Scanner;

/**
 * first successful story by analyzing it and coming up an algorithm by myself
 * --even though I saw this question before, I basically forgot.
 * Them came up a acceptable solution within 15 minutes.
 * Cheers to myself.
 *
 * Thinking ways of solutions with 0 coins, then 1 coins, then 2 coins... until with coins.length coins
 * money wise, starting from 0, to 1 , to 2, ... to amount
 *
 * define dp[c][m] as accumulated solutions of c coins and m amount
 *
 *
 * @author Andrew Ma
 *
 */
public class CoinChangeNumOfWays {
	
    public static long makeChange(int[] coins, int money) {
    	//I assumes 0 denom having 0 solutions to any amount of money
    	//also assumes any denom having 0 solution to 0 amount of money
    	//it turns out to be right! to be brave to make assumptions!

    	//making room for 0 denom and 0 amount of money
        long dp[][] = new long[coins.length+1][money+1];
        //initialization the known solutions
        //0 denom, 0 solution to any amount of money
        for(int i=0;i<money+1;i++){
            dp[0][i]=0;//no solution for each money value
        }
        //0 money value, 0 solution with any value of denomination
        for(int i=0;i<coins.length+1;i++){
            dp[i][0]=0;//no coin can do 0 change
        }

        //then build up values towards to final solution
        //c and m here are for dp array's dimensions
        /*
         * define dp[c][m] as accumulated solutions at c and m, real number of coins(order according to array), and real money amount
         * since we need to build it up from previous, so making c=0 and m=0 for that purpose
         * dp[c][m]=
         * 			1. dp[c-1][m]. when money value is less than denom, then get solution from last denom
         * 							--here it needs to have a 0 denom
         * 			2. dp[c-1][m]+1. when money value equals denom value, we get one more solution
         * 			3. dp[c-1][m] + dp[c][m-coins[c-1]]. when money value is greater than denom value,
         * 							it adds up accumulation from previous denom and same denom for money value
         * 							not including this denom.
         * shit, when did I become so able to analyze? I guess drawing it out and practice on paper or white board
         * really helped to see one's thoughts and then you just need to write the code to reflect the thoughts. 
         * and you are confident that you are able to write code to reflect your thoughts.
         */
        for(int c=1;c<coins.length+1;c++){
            for(int m=1;m<money+1;m++){
            	//when it comes to refer values in coins, c need to be converted back to 0 based
                if(m<coins[c-1]){
                    //no change at this, copy the last denom's accumulated solutions
                    dp[c][m] = dp[c-1][m];
                }
                else if(m==coins[c-1]){
                    //we get one more solution
                    dp[c][m] = dp[c-1][m] +1;
                }
                else //if (m>coins[c-1])
                    {
                    //then it is last denom's accumulated solution + lesser value's accumulated solution
                    dp[c][m] = dp[c-1][m] + dp[c][m-coins[c-1]];
                }
            }
        }
        return dp[coins.length][money];
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for(int coins_i=0; coins_i < m; coins_i++){
            coins[coins_i] = in.nextInt();
        }
        System.out.println(makeChange(coins, n));
    }
}