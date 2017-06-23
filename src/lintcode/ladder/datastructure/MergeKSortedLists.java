package lintcode.ladder.datastructure;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
	class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int val) {
		          this.val = val;
		          this.next = null;
		      }
		  }
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null) {
                return -1;
            }
            return left.val - right.val;
        }
    };
    
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        //adding first node from each list
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        //helper node
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            p.next = head;
            p = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }

}

/*


Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.

*/