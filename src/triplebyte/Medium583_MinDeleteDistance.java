package triplebyte;

import java.util.*;

/**
 * only deletion is allowed
 */
public class Medium583_MinDeleteDistance {
	public static int minDistance(String str1, String str2) {
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
    //my another way. what's left are not in common, thus needed to be deleted in order to be in common.
    static int deleteDistance(String str1, String str2){
	    Map<Character,Integer> str1Map = new HashMap<Character,Integer>();
        Map<Character,Integer> str2Map = new HashMap<Character,Integer>();
        for(char c:str1.toCharArray()) {
            str1Map.put(c,str1Map.getOrDefault(c,0)+1);
        }
        for(char c:str2.toCharArray()) {
            str2Map.put(c,str2Map.getOrDefault(c,0)+1);
        }
        Set<Character> keySet = new HashSet<>();
        keySet.addAll(str1Map.keySet());
        keySet.addAll(str2Map.keySet());
        for(Character c:keySet){
            if(str1Map.containsKey(c)&&str2Map.containsKey(c)){
                if(str1Map.get(c)==str2Map.get(c)){
                    str1Map.remove(c);
                    str2Map.remove(c);
                }else if(str1Map.get(c)<str2Map.get(c)){
                    str2Map.put(c,str2Map.get(c)- str1Map.get(c));
                    str1Map.remove(c);
                }else{
                    str1Map.put(c,str1Map.get(c)- str2Map.get(c));
                    str2Map.remove(c);
                }
            }
        }
        int ans = 0;
        for(Character c:str1Map.keySet()){
            ans+=c.charValue()*str1Map.get(c);
        }
        for(Character c:str2Map.keySet()){
            ans+=c.charValue()*str2Map.get(c);
        }
        return ans;
    }
    public static void main(String[] args){
     System.out.println(deleteDistance("cat","at"));
        System.out.println(deleteDistance("abba","abc"));
        System.out.println(minDistance("abba","abc"));
    }
}
