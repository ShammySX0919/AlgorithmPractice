package leetcode.contest.week32;

public class Medium583_MinDeleteDistance {
	public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        
        // dp[i][j] stands for distance of first i chars of word1 and first j chars of word2
        int[][] dp = new int[len1 + 1][len2 + 1];
        ///deleting i characters from str1 to match empty str2
        for (int i = 0; i <= len1; i++)
            dp[i][0] = i;
        //delete j character from string 2 to match empty str1
        for (int j = 0; j <= len2; j++)
            dp[0][j] = j;
            
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(
                    		Math.min(dp[i - 1][j - 1] + 2,//delete one character from both string as same end 
                    						dp[i - 1][j] + 1),//delete one from string 2 
                    		dp[i][j - 1] + 1);//delete 1 from string 1
            }
        }
        
        return dp[len1][len2];
    }
}
