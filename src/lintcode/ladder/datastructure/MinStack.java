package lintcode.ladder.datastructure;

import java.util.Stack;

public class MinStack {
    Stack<Integer> s = new Stack<Integer>();
    int min = Integer.MIN_VALUE;
    public MinStack() {
        // do initialize if necessary
    }

    public void push(int number) {
        // write your code here
        if(s.isEmpty() ||number <min){
            min = number;
        }
       
        s.push(number);
        s.push(min);
    }

    public int pop() {
        // write your code here
        int num;
        if(s.isEmpty()){
            min= Integer.MIN_VALUE;
            num = min;
        }else{
            s.pop();//pop out min
            num = s.pop();
            if(s.isEmpty()){
                min= Integer.MIN_VALUE;
            }
            else{
                min = s.peek();
            }
        }
        return num;
    }

    public int min() {
        // write your code here
        return min;
    }
}
/**


Implement a stack with min() function, which will return the smallest number in the stack.

It should support push, pop and min operation all in O(1) cost.
Notice

min operation will never be called if there is no number in the stack.

Example

push(1)
pop()   // return 1
push(2)
push(3)
min()   // return 2
push(1)
min()   // return 1



*/