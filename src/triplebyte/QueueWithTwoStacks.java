package triplebyte;

import java.util.Stack;

/**
 * Created by 212595974 on 6/27/2017.
 */
public class QueueWithTwoStacks<T> {
    Stack<T> inStack = new Stack<>();
    Stack<T> outStack = new Stack<>();
    public void enqueue(T e){
        inStack.push(e);
    }
    public T dequeue(){
        if(outStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        if(!outStack.isEmpty())outStack.pop();
        return null;
    }
}


//if you need to realize a stack, a stack node is like this
class StackNode<T>{
    T data;
    StackNode next;
}
//a stack is a class that maintains tail of StackNode
class MyStack<T>{
    StackNode header = new StackNode();//dummy tail
    public void push(T data ){
        StackNode n = new StackNode();
        n.data=data;
        n.next = header.next;
        header.next=n;
    }
    public StackNode<T> pop(){
        StackNode n = header.next;
        if(header.next!=null)
            header.next = header.next.next;
        return n;
    }
}