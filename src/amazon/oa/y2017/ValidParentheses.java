package amazon.oa.y2017;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode easy20
 * Created by andrew on 5/15/2017.
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if(s==null||s.length()==0)return true;
        Stack<Character> parentheses = new Stack<>();
        Map<Character,Character> validParentheses = new HashMap<>();
        validParentheses.put(')','(');
        validParentheses.put('}','{');
        validParentheses.put(']','[');
        for(int i=0;i<s.length();i++){
            //open parentheses
            Character c = s.charAt(i);
            if(validParentheses.values().contains(c)){
                parentheses.push(c);
            }
            //close parentheses, check validity:must match top of stack
            else if(validParentheses.containsKey(c)){
                if(parentheses.isEmpty())return false;
                if(!parentheses.peek().equals(validParentheses.get(c)))return false;
                parentheses.pop();
            }
        }
        return parentheses.isEmpty();
    }
}
