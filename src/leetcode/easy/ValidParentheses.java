package leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by 212595974 on 3/28/2017.
 */
public class ValidParentheses {
    public static boolean isValid(String str){
        Objects.requireNonNull(str);
        if(str.length()==0)return true;
        //create a map of valid pairs with value initialized
        Map<Character,Character> validPairs = new HashMap<Character,Character>(){
            {
                put('(',')');
                put('[',']');
                put('{','}');
            }
        };
        //stack utility
        Stack helper = new Stack<Character>();
        for(char c:str.toCharArray()){
            //only for characters interested in
            if(validPairs.containsKey(c)){
                //push to stack the left parentheses
                helper.push(c);
            }else if(validPairs.values().contains(c)){//right parentheses, time to check validity
                //stack should not be empty and its top should match current character
                if(helper.empty() || validPairs.get(helper.pop())!=c){
                    return false;
                }
            }
        }
        return helper.empty();
    }
    public static void main(String... args){
        System.out.println(ValidParentheses.isValid("a(bc)d"));
        System.out.println(ValidParentheses.isValid("a(bc){[}d"));
        System.out.println(ValidParentheses.isValid("a(bc){[]}d"));
        System.out.println(ValidParentheses.isValid("a(bc){[)]}d"));
    }
}
