package amazon.easy;
//Reverse a singly linked list.
public class LeetCode206_ReverseLinkedList {
	static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
    public ListNode reverseList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);//dummy head of new list
        dummyHead.next = null;//original header of new list
        while(head!=null){
            ListNode previousNewHead = dummyHead.next;
            ListNode nextNodeInOriginalList = head.next;//next node in original list
            dummyHead.next = head;//appending new node before new head
            head.next = previousNewHead;//point new head to previous new head
            head = nextNodeInOriginalList;//move original head to its original next
        }
        return dummyHead.next;
    }
}
