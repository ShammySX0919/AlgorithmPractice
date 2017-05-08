package triplebyte;

import java.util.Objects;

/**
 * Created by andrew on 5/7/2017.
 */
public class LevenshteinEditDistance {
    int levenshteinDistanceRecursion(String str1, String str2){
        Objects.requireNonNull(str1);
        Objects.requireNonNull(str2);
        int cost;
        //base case, empty string
        if(str1.length()==0)return str2.length();
        if(str2.length()==0)return str1.length();
        //from right to left
        //test if last characters are same
        if(str1.charAt(str1.length()-1)==str2.charAt(str2.length()-1))
            cost = 0;//same, no action
        else
            cost = 1;//replace
        String str1Prefix = str1.substring(0,str1.length()-1);//last character is exclusive
        String str2Prefix = str2.substring(0,str2.length()-1);
        return Math.min(
                    Math.min(levenshteinDistanceRecursion(str1Prefix,str2)+1,//delete char from str1
                            levenshteinDistanceRecursion(str1,str2Prefix)+1//delete char from str2. or insert char to str1
                    ),
                    levenshteinDistanceRecursion(str1Prefix,str2Prefix)+cost //substitute
                );
    }
    int levenshteinDistanceDp(String str1,String str2){
        Objects.requireNonNull(str1);
        Objects.requireNonNull(str2);
        int substitutionCost = 0;
        //dp[i,j] will hold the Levenshtein distance between
        // the first i characters of str1 and the first j characters of str2
        // note that dp has (m+1)*(n+1) values
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        //when read verbs, the subject is str1
        // source prefixes can be transformed into empty string by
        // dropping all characters
        for (int i=0;i<str1.length()+1;i++)
            dp[i][0] = i;
        // target prefixes can be reached from empty source prefix
        // by inserting every character
        for (int j=0;j<str2.length()+1;j++)
            dp[0][j] = j;
        for (int j=1;j<str2.length()+1;j++)
            for (int i=1;i<str1.length()+1;i++) {
                if (str1.charAt(i) == str2.charAt(j))
                    substitutionCost=0;
                else
                    substitutionCost=1;
                //for str1 i position to str2 j position
                dp[i][j] =Math.min(
                        //delete str1 i
                        Math.min(dp[i - 1][j]+1,              // deletion
                                //or delete str2 j<==>insert str1 i
                                dp[i][ j - 1]+1),             // insertion
                        //or replace str1 i with str2 j
                        dp[i - 1][ j - 1]+substitutionCost);  // substitution
            }
        return dp[str1.length()][str2.length()];
    }
}
