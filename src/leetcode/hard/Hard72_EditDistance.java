package leetcode.hard;

import java.util.Objects;

/**
 * Created by andrew on 5/15/2017.
 */
public class Hard72_EditDistance {
    /*
    public int minDistance(String str1, String str2) {
        Objects.requireNonNull(str1);
        Objects.requireNonNull(str2);
        int substitutionCost = 0;
        //dp[i,j] will hold the Levenshtein distance between
        // the first i characters of str1 and the first j characters of str2
        // note that dp has (m+1)*(n+1) values
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        //str2 is empty, need to delete characters from str1
        for (int i=0;i<str1.length()+1;i++)
            dp[i][0] = i;
        //str1 is empty , need to delete characters from str2
        for (int j=0;j<str2.length()+1;j++)
            dp[0][j] = j;
        //for each str1 and str2's characters
        for (int i=1;i<str1.length()+1;i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                //compare characters from both string at i in str1 and j in str2
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    substitutionCost = 0;
                else
                    substitutionCost = 1;
                //if they are not same
                dp[i][j] = Math.min(
                        //then either delete from str1 (<==> insert into str2)
                        Math.min(dp[i - 1][j] + 1,              // deletion
                                //or delete from str2 (<==>insert into str1 )
                                dp[i][j - 1] + 1),             // insertion
                        //or replace str1 i with str2 j (<==>replace str2's j with str1's i)
                        dp[i - 1][j - 1] + substitutionCost);  // substitution
            }
        }
        return dp[str1.length()][str2.length()];
    }
*/
    /**
     * this is same as above, but I like the style of this code, easier to understand.
     * this one can also be easily converted to min delete distance, just replace 1 in substitution as 2 for deleting only distance
     * @param str1
     * @param str2
     * @return
     */
    public int minDistance(String str1, String str2) {
        Objects.requireNonNull(str1);
        Objects.requireNonNull(str2);
        int substitutionCost = 0;
        //dp[i,j] will hold the Levenshtein distance between
        // the first i characters of str1 and the first j characters of str2
        // note that dp has (m+1)*(n+1) values
        int[][] dp = new int[str1.length()+1][str2.length()+1];

        //str2 is empty, need to delete characters from str1
        for (int i=0;i<str1.length()+1;i++)
            dp[i][0] = i;
        //str1 is empty , need to delete characters from str2
        for (int j=0;j<str2.length()+1;j++)
            dp[0][j] = j;
        //for each str1 and str2's characters
        for (int i=1;i<str1.length()+1;i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                //I like this style of writing, easier to understand
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    //for str1 i position to str2 j position
                    dp[i][j] = Math.min(
                            //for the verbs, the subject is str1
                            //delete str1 i
                            Math.min(dp[i - 1][j] + 1,              // deletion
                                    //or delete str2 j<==>insert str1 i
                                    dp[i][j - 1] + 1),             // insertion
                            //or replace str1 i with str2 j
                            dp[i - 1][j - 1] + 1);  // substitution. this is the only difference between edit and delete distance
                //for delete, this is + 2 because you need to delete one character from each string.
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
