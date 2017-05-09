package triplebyte;

/**
 * Created by andrew on 5/8/2017.
 */
class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
}

/**
 * Node itself is a singly linked list already.
 * creating another class that has a head node to make operations easier
 */
public class LinkedList {
    Node head;
    //append in singly linked list is O(n)
    public void append(int data){
        if(head==null){
            head = new Node(data);
            return;
        }

        Node current = head;

        while(current.next!=null){
            current= current.next;
        }
        current.next = new Node(data);
    }
    //prepend in singly linked list is O(1)
    //it is a place to benefit a header
    public void prepend(int data){
        Node newHead = new Node(data);
        newHead.next = head;
        head=newHead;
    }
    public void deleteWithValue(int data){
        if(head==null)return;
        if(head.data==data){
            head = head.next;
            return;
        }
        Node current = head;
        //nodes after header
        while(current.next!=null){
            if(current.next.data==data){
                current.next = current.next.next;
                return;
                //delete only first node with data value.
                // ignore this will loop until end and delete all nodes with same data
            }
            current = current.next;
        }
    }
}
