package expedia.onsite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestRepeatedSubstring {
public static String getLongestRepeatedSubstring(String input){
	if(input==null||input.length()<2)return "";
	int maxLen = input.length()/2;
	//checking repeat substring from max len to smaller len
	Map<String,Integer> stats = new HashMap<>();//using map for statistics collection
	//set is better for this solution
	while(maxLen>0){
		stats.clear();//reset statistics for each length
		for(int i=0;i<=input.length()-maxLen;i=i+maxLen){
			String substring = input.substring(i,i+maxLen);
			if(!stats.containsKey(substring))
				stats.put(substring, 1);
			else
			{
				return substring;
			}
		}
		maxLen--;
	}
	return "";
}
public static String getLongestRepeatedSubstringBetter(String input){
	if(input==null||input.length()<2)return "";
	int maxLen = input.length()/2;
	//checking repeat substring from max len to smaller len
	Set<String> stats = new HashSet<>();//using map for statistics collection
	//set is better for this solution
	while(maxLen>0){
		stats.clear();//reset statistics for each length
		for(int i=0;i<=input.length()-maxLen;i=i+maxLen){
			String substring = input.substring(i,i+maxLen);
			if(!stats.add(substring))
			{
				return substring;
			}
		}
		maxLen--;
	}
	return "";
}
public static void main(String args[]){
	System.out.println(getLongestRepeatedSubstring("aabbbccccc"));
	System.out.println(getLongestRepeatedSubstring("ABABA"));
	System.out.println(getLongestRepeatedSubstringBetter("A"));
	System.out.println(getLongestRepeatedSubstringBetter("AA"));
	System.out.println(getLongestRepeatedSubstringBetter("AB"));
	System.out.println(getLongestRepeatedSubstringBetter("aabbbccccc"));
	System.out.println(getLongestRepeatedSubstringBetter("ABABA"));
}
}
