package leetcode;

import java.util.Stack;

/**
 * Created by Andrew Ma on 10/27/2016.
 */
public class Medium439_TernaryExpressionEvaluate {
    Character evaluate(Character[] ternaryExpression){
      //  Objects.requireNonNull(ternaryExpression);
        if(ternaryExpression.length!=5){
            throw new IllegalArgumentException("must be 5 characters");
        }
        //T?v1:v2
        if('T'==ternaryExpression[0]){
            return ternaryExpression[2];
        }else{
            return ternaryExpression[4];
        }
    }
    public void parseTernary(String expression)
    {
        Character[] exp = new Character[5];
        Stack<Character> stack = new Stack<Character>();
        //put expression into stack
        for(int i=0;i<expression.length();i++){
            stack.push(expression.charAt(i));
        }
        //stop at size 1 becuase that'd be result:
        //taking 5 one stack, push result back
        while(stack.size()>1){
            for(int i=4;i>=0;i--){
                exp[i]=stack.pop();
            }
            Character r = evaluate(exp);
            stack.push(r);
        }
        System.out.println(stack.pop());
    }
    public static void main(String[] args){
        Medium439_TernaryExpressionEvaluate o = new Medium439_TernaryExpressionEvaluate();
        o.parseTernary("F?1:T?4:5");
    }
}
