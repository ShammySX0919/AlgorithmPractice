package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * originally used Map<Characer,Integer> to serve the stats purpose, but rum out of time.
 * array is way faster than map
 * Created by andrew on 25/10/16.
 */
public class Easy438_FindAllAnagramsInAString {
    int[] buildStats(String s, int start, int len) {
        int[] stats = new int[26];
        for (int i = start; i < start + len; i++) {
            stats[s.charAt(i) - 'a']++;
        }
        return stats;
    }

    private boolean featureMatch(String s, int index, int len, int[] stats) {
        int[] newStats = new int[26];
        System.arraycopy(stats, 0, newStats, 0, 26);
        for (int i = index; i < index + len; i++) {
            char c = s.charAt(i);
            newStats[c - 'a']--;
            if (newStats[c - 'a'] < 0) return false;
        }
        for (int i = 0; i < 26; i++) {
            if (newStats[i] > 0) return false;
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || s.length() < p.length()) return result;
        //build up pattern feature
        int[] statsP = buildStats(p, 0, p.length());

        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (featureMatch(s, i, p.length(), statsP)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * I still feel hard to understand. put it here for review later
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsSmart(String s, String p) {
        int[] chars = new int[26];
        List<Integer> result = new ArrayList<Integer>();

        if (s == null || p == null || s.length() < p.length())
            return result;
        for (char c : p.toCharArray())
            chars[c - 'a']++;

        // Initialize the window
        int start = 0, end = p.length(), count = p.length();
        for (int i = 0; i < p.length(); i++)
            if (chars[s.charAt(i) - 'a']-- > 0)
                count--;
        if (count == 0)
            result.add(0);

        // Go over the string
        while (end < s.length()) {
            if (chars[s.charAt(start++) - 'a']++ == 0)
                count++;
            if (--chars[s.charAt(end++) - 'a'] == 0)
                count--;
            if (count == 0)
                result.add(start);
        }

        return result;
    }
}