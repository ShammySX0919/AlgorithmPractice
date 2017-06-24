package lintcode.ladder.string;

import java.util.HashMap;
import java.util.Map;

public class TwoStringsAreAnagrams {
	/**
     * @param s: The first string
     * @param b: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        if(s==null || t==null)return false;
        if(s.length()!=t.length())return false;
        Map<Character,Integer> stat = new HashMap<Character,Integer>();
        for(int i=0;i<s.length();i++){
            stat.put(s.charAt(i),
                (stat.get(s.charAt(i))==null?1:stat.get(s.charAt(i))+1));
        }
        for(int i=0;i<t.length();i++){
            if(stat.get(t.charAt(i))==null)return false;
            if(stat.get(t.charAt(i))-1==0){
                stat.remove(t.charAt(i));
            }
            else{
                stat.put(t.charAt(i),stat.get(t.charAt(i))-1);
            }
        }
        if(stat.size()!=0)return false;
        return true;
    }
}
/*

Write a method anagram(s,t) to decide if two strings are anagrams or not.
Have you met this question in a real interview?
Clarification

What is Anagram?
- Two strings are anagram if they can be the same after change the order of characters.
Example

Given s = "abcd", t = "dcab", return true.
Given s = "ab", t = "ab", return true.
Given s = "ab", t = "ac", return false.

*/