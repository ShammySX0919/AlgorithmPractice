package amazon.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrew on 5/25/2017.
 */
public class EliminateOwels {
    public String eliminateOwels(String str){
        StringBuilder sb = new StringBuilder();
        String v = "aeiouAEIOU";
        //build a set to more efficiently check vowels
        Set<Character> vowels = new HashSet<>();
        for(char c:v.toCharArray()){
            vowels.add(c);
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!vowels.contains(c))
                sb.append(c);
        }
        return sb.toString();
    }
}
