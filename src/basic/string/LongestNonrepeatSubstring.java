package basic.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrew on 6/13/2017.
 */
public class LongestNonrepeatSubstring {
    public static int lengthOfLongestSubstringSet(String s) {
        Set<Character> charSet = new HashSet<Character>();
        int i = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            while (charSet.contains(s.charAt(j))) {
                charSet.remove(s.charAt(i));
                i++;
            }
            charSet.add(s.charAt(j));
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringSet("abcdefabcfabcdefhijka"));

    }
}
