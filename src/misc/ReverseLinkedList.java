package misc;
class Node{
	    int val;
	    public Node(int val){
	        this.val = val;
	    }
	    Node next;
	}
public class ReverseLinkedList {
	

	Node reverse(Node head){
	    if(head == null)return null;
	    //dummy head
	    Node newHead = new Node(-1);
	    
	    Node tn = head;
	    while(tn!=null){
	        Node oldHeader = newHead.next;
	        //not destroying old 
	        Node curNode = new Node(tn.val);
	        newHead.next = curNode;
	        curNode.next = oldHeader;
	        tn = tn.next;
	    }
	    return newHead.next;
	}
	public static void main(String... args){
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		one.next=two;
		two.next=three;
		ReverseLinkedList o = new ReverseLinkedList();
		Node head = o.reverse(one);
		while(head!=null){
			System.out.println(head.val+"-->");
			head = head.next;
		}
	}
}
