package amazon.oa.y2017;

import javax.management.RuntimeMBeanException;
import java.util.Objects;
import java.util.Stack;

/**
 * every element is a score, this is to replace letter with real score
 * Created by andrew on 5/15/2017.
 */
public class BaseballResult {
    private static void checkValid(Stack<Integer> s){
        if(s.empty())
            throw new RuntimeException("Invalid operation");
    }
    public static int getResult(String[] instructions){
        Objects.requireNonNull(instructions);
        Stack<Integer> result = new Stack<>();
        for(String s:instructions){
            //Z means remove previous score
            if("Z".equals(s)){
                if(!result.empty()) {
                    result.pop();
                }
            }
            //X means current score is double of previous score
            else if("X".equals(s)){
                Integer t = result.empty()?0:result.peek();
                result.push(t*2);
            }
            //+ means sum of prevous two scores
            else if("+".equals(s)){
                boolean pushBack = false;
                if(!result.empty())
                    pushBack = true;
                Integer t1 = result.empty()?0:result.pop();
                Integer t2 = result.empty()?0:result.peek();
                if(pushBack) {
                    result.push(t1);
                }
                result.push(t1+t2);
            }else{
                result.push(Integer.parseInt(s));
            }
        }
        int res = 0;
        while(!result.empty()){
            res+=result.pop();
        }
        return res;
    }
    public static void main(String[] args){
        String[] ins = new String[]{"5", "-2", "4", "Z", "X", "9", "+", "+"};
        System.out.println(getResult(ins));
    }
}
