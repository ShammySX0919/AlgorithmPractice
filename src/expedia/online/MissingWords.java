package expedia.online;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MissingWords {
public static String[] findMissingWords(String s, String t){
	String[] res = null;
	Set<String> tSet = new HashSet<>(Arrays.asList(t.split(" ")));
	Set<String> sSet = new LinkedHashSet<>(Arrays.asList(s.split(" ")));
	sSet.removeAll(tSet);
	res = new String[sSet.size()];
	int i =0;
	for(String str:sSet){
		res[i]=str; i++;
	}
	return res;
}
public static void main(String str[]){
	System.out.println(Arrays.asList(findMissingWords("I like cheese","cheese")));
}
}
