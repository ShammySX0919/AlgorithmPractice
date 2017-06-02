package amazon.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrew on 5/30/2017.
 */
public class LeetCode3_LongestSubstringWithoutRepeatChar {
    public int lengthOfLongestSubstring(String s) {
        if(s==null)return -1;
        if(s.isEmpty())return 0;
        //this set contains only one check's visiting condition
        //if the character set is small, array could be used
        Set<Character> charSet = new HashSet<Character>();
        int maxLen=0,h=0,t;String longestStr="";

        //having two pointers, one for head adjustment, one for substring tail adjustment
        //end of substring is marked by first repeated character
        //for character being repeated, its left side positions won't construct longer string
        //then current one, so it is safe to start from its next position. that  means, header
        // can be moved to next position of character being repeated
        while (h<s.length()){
            //the inner loop will end when first repeat character is found
            //that also marks end of substring currently being checked
            //it start from header+number of characters between the repeated characters
            t=h+charSet.size();
            //scan more from t and move t towards tail
            while(t<s.length() && !charSet.contains(s.charAt(t))){
                charSet.add(s.charAt(t));
                t++;
            }

            maxLen = Math.max(maxLen, charSet.size());
            if(t==s.length())break;
            if (t-h+1>maxLen)longestStr=s.substring(h,t);
            //advance h to character being repeated, removing characters from charSet at same time
            //because new string start from right of character being repeated
            while(h<s.length() && s.charAt(h)!=s.charAt(t)){
                charSet.remove(s.charAt(h));
                h++;
            }
            //move to new substring start position
            h++;
        }
        return maxLen;
    }
}
