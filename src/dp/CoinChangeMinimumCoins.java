package dp;

/**
 * Created by andrew on 6/10/2017.
 */
public class CoinChangeMinimumCoins {
    // m is size of coins array (number of different coins)

    static int minCoinsRec(int coins[], int amount, int dp[])
    {
        if(dp[amount]!=0)return dp[amount];
        // base case
        if (amount == 0) {
            dp[0] = 0;
            return dp[0];
        }

        // Initialize result
        int res = Integer.MAX_VALUE;

        // Try every coin that has smaller value than amount
        for (int i=0; i<coins.length; i++)
        {
            if (coins[i] <= amount)
            {
                //try solution for amount  - coints[i], the current coin denom
                int sub_res = minCoinsRec(coins, amount-coins[i],dp);
                //if there is a soluton, the solution to amount is res+1(with current coin)
                // Check for INT_MAX to avoid overflow and see if
                // result can minimized
                if (sub_res != Integer.MAX_VALUE && sub_res + 1 < res)
                    res = sub_res + 1;//res keeps the minimum value through the process
            }
        }
        dp[amount]=res;
        return dp[amount];
    }
    static int minCoins(int coins[], int amount)
    {
        // dp[i] will be storing the minimum number of coins
        // required for i value.  So dp[amount] will have result
        int dp[] = new int[amount+1];

        // Base case (If given value V is 0)
        dp[0] = 0;

        // Initialize all dp values as Infinite
        for (int i=1; i<=amount; i++)
            dp[i] = Integer.MAX_VALUE;

        // Compute minimum coins required for all
        // values from 1 to amount
        for (int i=1; i<=amount; i++)
        {
            // Go through all coins smaller than i
            for (int j=0; j<coins.length; j++)
                if (coins[j] <= i)//must be amount >=a coin
                {
                    int sub_res = dp[i-coins[j]];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < dp[i])
                        dp[i] = sub_res + 1;//new smaller value
                }
        }
        return dp[amount];
    }
    public static void main(String args[])
    {
        int coins[] =  {9, 6, 5, 1};
        int m = coins.length;
        int V = 11;
        int dp[] = new int[V+1];
        System.out.println("Minimum coins required is "+ minCoinsRec(coins, V,dp) );
        System.out.println("Minimum coins required is "+ minCoins(coins, V) );
    }
}
