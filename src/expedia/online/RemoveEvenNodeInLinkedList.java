package expedia.online;

public class RemoveEvenNodeInLinkedList {
	static class Node {
		Node next;
		int data;

		Node(int d) {
			data = d;
		}
	}

	public static void removeEvenNodes(Node n) {
		//we do not need to change head node, so that n can be used out of function
		if (n == null)
			return;
		Node p = n;
		while (p != null && p.next != null) {
			p.next = p.next.next;
			p = p.next;
		}
		return;
	}
	
	public static Node removeOddNodes(Node n) {
		//parameter's node is still the same, we need to return a new node
		if(n==null||n.next==null){n=null;return null;}
		if(n!=null &&n.next==null)return n;
		n=n.next;
		removeEvenNodes(n);
		return n;
	}
	public static void main(String[] args) {
		Node n = new Node(1);
		Node n2 = new Node(2);
		 n.next = n2;
		Node n3 = new Node(3);
		 n2.next = n3;
		Node n4 = new Node(4);
		 n3.next=n4;
		Node n5 = new Node(5);
		 n4.next=n5;
		//removeEvenNodes(n);
		n=removeOddNodes(n);
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}
}
