package amazon.easy;

import java.util.Stack;

/**
 * Created by 212595974 on 6/2/2017.
 */
public class Leetcode155_MinStack {
    int min=Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if(x <= min){//it has to be <= with = considered for the repeat values
            //with smaller value coming in, push the previous min,
            stack.push(min);
            min=x;//change min to new min
        }
        stack.push(x);//then push the current value
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.peek()==min) {//next value to pop is the current min
            stack.pop();//pop it
            min=stack.peek();//restore min
            stack.pop();
        }else{//otherwise, just pop
            stack.pop();
        }
        //do not forget to reset initial min value after stack is empty
        if(stack.empty()){
            min=Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
    public static void main(String... args){
        Leetcode155_MinStack o = new  Leetcode155_MinStack();
        o.push(1);
        o.push(2);
        o.push(2);
        o.push(2);
        System.out.println(o.getMin());
        o.pop();
        System.out.println(o.getMin());
        o.pop();
        System.out.println(o.getMin());
        o.pop();
        System.out.println(o.getMin());
    }
}
