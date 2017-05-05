package amazon.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by andrew on 4/29/2017.
 * I am still good at algorithms :)
 */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
public class LeetcodeHard23_MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0)return null;
        if(lists.length==1)return lists[0];
        int k = lists.length;
        //have a min pq that sort by listnode.val
        PriorityQueue<ListNode> pq = new PriorityQueue<>(k,
                new Comparator<ListNode>(){
                    public int compare(ListNode e1, ListNode e2){
                        return e1.val - e2.val;
                    }
                });
        //adding head of each list to pq
        for(int i=0;i<k;i++){
            if(lists[i]!=null){
                pq.add(lists[i]);
            }
        }
        //have a head node for easy manipulation
        ListNode head = new ListNode(-1);
        //cursor to current merged nodelist
        ListNode curCursor = null;
        while(!pq.isEmpty()){
            //getting min element from pq
            ListNode curNode = pq.poll();
            //first element, link to head
            if(head.next==null){
                head.next = curNode;
                curCursor = head.next;
            }else{
                //second node and on, link it to merged node list
                curCursor.next = curNode;
                curCursor = curCursor.next;
            }
            //adding the list's next ndoe
            if(curNode.next!=null){
                pq.add(curNode.next);
            }
        }
        return head.next;
    }
}
