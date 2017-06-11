package leetcode.contest.week36;

/**
 * Created by andrew on 6/10/2017.
 */
public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] imos = new int[n+1];
        for(String d : dict){
            char[] q = d.toCharArray();
            int[] kmp = kmpTable(q);
            int p = 0;
            for(int i = 0;i < ss.length;i++){
                while(p >= 0 && q[p] != ss[i])p = kmp[p];
                if(++p == q.length){
                    imos[i-q.length+1]++;
                    imos[i+1]--;
                    p = kmp[p];
                }
            }
        }
        for(int i = 0;i < n;i++)imos[i+1] += imos[i];
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i = 0;i < n;i++){
            if(imos[i] > 0){
                temp.append(ss[i]);
            }else{
                if(temp.length() > 0){
                    sb.append("<b>");
                    sb.append(temp);
                    sb.append("</b>");
                    temp = new StringBuilder();
                }
                sb.append(ss[i]);
            }
        }
        if(temp.length() > 0){
            sb.append("<b>");
            sb.append(temp);
            sb.append("</b>");
        }
        return sb.toString();
    }

    public int[] kmpTable(char[] str)
    {
        int n = str.length;
        int[] kmp = new int[n+1];
        kmp[0] = -1; kmp[1] = 0;
        for(int i = 2, j = 0;i <= n;i++){
            while(j > 0 && str[i-1] != str[j])j = kmp[j];
            kmp[i] = str[i-1] == str[j] ? ++j : 0;
        }
        return kmp;
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