package leetcode.easy;

import java.util.HashMap;

/**
 * Created by andrew on 6/11/2017.
 */
public class Easy266_PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        HashMap < Character, Integer > map = new HashMap< >();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = 0;
        for (char key: map.keySet()) {
            count += map.get(key) % 2;
        }
        return count <= 1;
    }
}
