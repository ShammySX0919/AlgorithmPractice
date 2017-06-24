package lintcode.ladder.string;

import java.util.HashMap;
import java.util.Map;

public class CompareStrings {
	/**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
      
        Map<Character,Integer> stat = new HashMap<Character,Integer>();
        for(int i=0;i<B.length();i++){
            stat.put(B.charAt(i),
                (stat.get(B.charAt(i))==null?1:stat.get(B.charAt(i))+1));
        }
        for(int i=0;i<A.length();i++){
            if(stat.get(A.charAt(i))==null)
                continue;
            if(stat.get(A.charAt(i))-1==0){
                stat.remove(A.charAt(i));
            }
            else{
                stat.put(A.charAt(i),stat.get(A.charAt(i))-1);
            }
        }
        if(stat.size()!=0)return false;
        return true;
    }
    //another solution is to check if sorted A contains sorted B
}
/*
Compare two strings A and B, determine whether A contains all of the characters in B.

The characters in string A and B are all Upper Case letters.

Notice

The characters of B in A are not necessary continuous or ordered.

Example

For A = "ABCD", B = "ACD", return true.

For A = "ABCD", B = "AABC", return false.
*/