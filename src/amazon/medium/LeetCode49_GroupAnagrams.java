package amazon.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note: All inputs will be in lower-case.

Solution: easy one: sort each word, group them together if their sorted values are same
 * @author Andrew
 *
 */
public class LeetCode49_GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs==null ||strs.length==0 )return result;
        Map<String, List<String>> group = new HashMap<String,List<String>>();
        for(String s: strs){
        	//sort characters in word
        	//converting string to char array
            char[] sc = s.toCharArray();
            //sort the char array
            Arrays.sort(sc);
            //make a new string from char array
            String ns = new String(sc);
            //using hashmap to group words according to their sorted keys
            if(group.containsKey(ns)){
                group.get(ns).add(s);
            }else{
                List<String> l = new ArrayList<String>();
                l.add(s);
                group.put(ns,l);
            }
        }
        for(List<String> l:group.values()){
            Collections.sort(l);
            result.add(l);
        }
        
        return result;
    }
}
