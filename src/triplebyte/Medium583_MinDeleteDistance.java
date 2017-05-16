package triplebyte;

/**
 * only deletion is allowed
 */
public class Medium583_MinDeleteDistance {
	public int minDistance(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();

        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        
        // dp[i][j] stands for distance of first i chars of str1 and first j chars of str2
        int[][] dp = new int[len1 + 1][len2 + 1];
        ///deleting i characters from str1 to match empty str2
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        //delete j characters from string 2 to match empty str1
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;
        //for other dp array elements, starting from 1, that is real str's index 0
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //i-1 and j-1 is to convert index to be 0 based
                //i and j is dp array index, real work meaning of index(1 based)
                //if two characters are same in both strings
                //then the distance is same as previous position
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else//otherwise, you can either delete from string1, or from string 2 or from both
                    dp[i][j] = Math.min(
                    		Math.min(dp[i - 1][j - 1] + 2,//delete from both strings
                    						dp[i - 1][j] + 1),//delete from str1
                    		dp[i][j - 1] + 1);//delete from string 2
            }
        }
        
        return dp[len1][len2];
    }
}
