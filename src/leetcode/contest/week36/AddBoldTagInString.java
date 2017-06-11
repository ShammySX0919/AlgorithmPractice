package leetcode.contest.week36;

/**
 * Created by andrew on 6/10/2017.
 */
public class AddBoldTagInString {
    /*
    public String addBoldTag(String s, String[] dict) {
        //not efficient but easy to understand
        int len = Integer.MAX_VALUE;
        //find min length of dict word
        for (String w : dict) {
            len = Math.min(len, w.length());
        }
        //a map of dict words within string
        int[] map = new int[s.length()];
        //it check existence of all dict work within string. this is the part very low efficient
        for (String w : dict) {
            int index = s.indexOf(w);
            while (index >= 0) {
                //mark dict word as 1s
                fill(map, index, w.length());
                //continue to find next occurrence of same word
                index = s.indexOf(w, index + 1);
            }
        }
        //build final answer
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            while (i < s.length() && map[i] == 0) {
                i++;
            }
            sb.append(s.substring(j, i));
            j = i;
            while (j < s.length() && map[j] == 1) {
                j++;
            }
            String toBeBolded = s.substring(i, j);
            if (toBeBolded.length() > 0) {
                sb.append("<b>").append(toBeBolded).append("</b>");
            }
            i = j;
        }
        return sb.toString();
    }

    public void fill(int[] map, int start, int len) {
        for (int i = start; i < start + len; i++) {
            map[i] = 1;
        }
    }
    */
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int len = 0;
            //for each string position, find maximum word length by check with all dict word
            for (String word : dict) {
                if (i + word.length() <= s.length()
                        && s.substring(i, i + word.length()).equals(word)) {
                    //like the max function here
                    len = Math.max(len, word.length());
                }
            }
            //if some words are found, mark the range as content should be bolded
            //boolean array here reflects overlap smartly
            for (int j = i; j < i + len; j++) {
                bold[j] = true;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }

        return result.toString();
    }
}

/*
616. Add Bold Tag in String

    User Accepted: 0
    User Tried: 0
    Total Accepted: 0
    Total Submissions: 0
    Difficulty: Medium

Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:

Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"

Example 2:

Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Note:

    The given dict won't contain duplicates, and its length won't exceed 100.
    All the strings in input have length in range [1, 1000].

 */