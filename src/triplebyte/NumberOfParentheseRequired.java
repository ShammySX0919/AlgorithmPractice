package triplebyte;

import java.util.Stack;

/**
 * Created by andrew on 5/7/2017.
 */
public class NumberOfParentheseRequired {
    public static int totalRequired(String str){
        Stack<Character> stack = new Stack<>();

        for(char c:str.toCharArray()){
            if('('==c) {
                stack.push(c);
            }
            else if(')'==c){
                if(stack.isEmpty()||stack.peek()!='('){
                    stack.push(c);
                }else
                    stack.pop();
            }
        }
        return stack.size();
    }
    public static void main(String[] args){
        System.out.println(NumberOfParentheseRequired.totalRequired(")("));
        //0
        System.out.println(NumberOfParentheseRequired.totalRequired("()"));
        System.out.println(NumberOfParentheseRequired.totalRequired("("));
        System.out.println(NumberOfParentheseRequired.totalRequired(")"));
        //3
        System.out.println(NumberOfParentheseRequired.totalRequired(")))"));
        //2
        System.out.println(NumberOfParentheseRequired.totalRequired("()))"));
        //1
        System.out.println(NumberOfParentheseRequired.totalRequired("(()))"));
    }
}
