package lintcode.ladder.datastructure;

import java.util.Stack;

public class QueueByTwoStacks {
	private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public QueueByTwoStacks() {
       // do initialization if necessary
       stack1 = new Stack<Integer>();
       stack2 = new Stack<Integer>();
    }
    
    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    public int pop() {
        // write your code here
        stack2.clear();
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        Integer result =  stack2.pop();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return result;
        
    }

    public int top() {
        // write your code here
        stack2.clear();
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        Integer result =  stack2.peek();
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return result;
    }
}

/**
 * 

As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.


 Example

push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2


*/
