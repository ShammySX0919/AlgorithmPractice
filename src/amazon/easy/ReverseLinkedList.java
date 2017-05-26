package amazon.easy;
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
	    //nextNode is used to traverse the linked list
	    Node nextNode = head;
	    while(nextNode!=null){
	        Node oldHeader = newHead.next;
	        //not destroying old 
	        Node curNode = new Node(nextNode.val);
	        //new head points to new node
	        newHead.next = curNode;
	        //new node points to old header
	        curNode.next = oldHeader;
	        //ready to process next node
	        nextNode = nextNode.next;
	    }
	    return newHead.next;
	}
//old list was changed to have only one left
	Node reverseInPlace(Node head){
		if(head == null)return null;
		//dummy head
		Node newHead = new Node(-1);

		Node curNode = head;
		while(curNode!=null){
			//keep old information
			Node oldHeader = newHead.next;
			Node nextNode = curNode.next;
			//adding current node to head of reversed list
			newHead.next = curNode;
			curNode.next = oldHeader;
			curNode = nextNode;
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
		head = one;
		while(head!=null){
			System.out.println(head.val+"-->");
			head = head.next;
		}
		head = o.reverseInPlace(one);
		while(head!=null){
			System.out.println(head.val+"-->");
			head = head.next;
		}

	}
}
