package lintcode.ladder.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
	/**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        Map<String,List<String>> stats = new HashMap<String,List<String>>();
        for(int i=0;i<strs.length;i++){
            String sorted = sortString(strs[i]);
            if(stats.get(sorted)==null){
                List<String> l=new ArrayList<String>();
                l.add(strs[i]);
                stats.put(sorted,l);
            }else{
                stats.get(sorted).add(strs[i]);
            }
        }
        List<String> result = new ArrayList<String>();
        for(
            Map.Entry<String,List<String>> en:stats.entrySet()
            ){
            if(en.getValue().size()>1){
                result.addAll(en.getValue());
            }
        }
        return result;
    }
    String sortString(String s){
        char[] sa = s.toCharArray();
        Arrays.sort(sa);
        return new String(sa);
        
    }
}
/*
Given an array of strings, return all groups of strings that are anagrams.

Notice

All inputs will be in lower-case

Example

Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

*/