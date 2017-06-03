package amazon.easy;

public class LeetCode160_IntersectOfTwoSinglyLnkedList {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		int aLen = 0, bLen = 0;
		ListNode nA = headA, nB = headB;
		// figure out length of the two
		while (nA != null) {
			aLen++;
			nA = nA.next;
		}
		while (nB != null) {
			bLen++;
			nB = nB.next;
		}
		// reset head
		nA = headA;
		nB = headB;
		// /let longer list move the first until the position the two list are
		// same length
		while (aLen > bLen) {
			nA = nA.next;
			aLen--;
		}
		while (bLen > aLen) {
			bLen--;
			nB = nB.next;
		}
		// move together
		while (nA != null && nB != null) {
			if (nA == nB)
				return nA;
			nA = nA.next;
			nB = nB.next;
		}
		return null;
	}
}
