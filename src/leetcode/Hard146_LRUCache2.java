package leetcode;

import java.util.HashMap;

/**
 * LRU，也就是least recently used，最近使用最少的；
 * 这样一个数据结构，能够保持一定的顺序，使得最近使用过的时间或者顺序被记录，
 * 实际上，具体每一个item最近一次何时被使用的，并不重要，重要的是在这样的一个结构中，item的相对位置代表了最近使用的顺序；
 * 满足这样考虑的结构可以是链表list或者数组array，不过前者更有利于insert和delete的操纵，
 * 此外，需要记录这个链表的head和tail，方便进行移动到tail或者删除head的操作，
 * 即：head.next作为最近最少使用的item，tail.prev为最近使用过的item，
 * 在set时，如果超出capacity，则删除head.next，同时将要插入的item放入tail.prev,
 * 而get时，如果存在，只需把item更新到tail.prev即可。

 这样set与get均为O(1)时间的操作 （HashMap Get/Set + LinkedList Insert/Delete)，空间复杂度为O(n),
 n为capacity。

 * Created by andrew on 13/12/16.
 */
public class Hard146_LRUCache2 {
    //node of doubly linked list, it contains key-value information. it is the cache with access sequence kept by dll
    //head is least recent used, tail is most recent used
    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    //class variables
    private int capacity;//cache capacity
    //another cache, for O(1) get, key-node, the node contains same key
    private HashMap<Integer, Node> hm = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);//use -1 for out-of-range key and values
    private Node tail = new Node(-1, -1);//actually they are dummy nodes, key and value are not important.

    //constructor with capacity
    public Hard146_LRUCache2(int capacity) {
        this.capacity = capacity;
        this.head.next = this.tail;//head and tail are originally linked
        this.tail.prev = this.head;
    }
    //get, move corresponding node to end of list:remove and add from/to DLL
    public int get(int key) {
        if (!hm.containsKey(key)) {
            return -1;
        }
        Node current = hm.get(key);
        //with dummy header and tail, it does not need to check nullness, COOL!
        //DLL remove a node of current. remove myself
        current.prev.next = current.next;
        current.next.prev = current.prev;
        //add it to tail because that's most recently used
        moveToTail(current);
    //return value from node
        return hm.get(key).value;
    }
    private void moveToTail(Node current) {
        current.next = tail;
        tail.prev.next = current;
        current.prev = tail.prev;
        tail.prev = current;
    }
    //set key-value, need to check capacity
    public void set(int key, int value) {
        //existing key
        //use get key to move it to tail if it exists. Smart!
        if (get(key) != -1) {
            //set new value
            hm.get(key).value = value;
            return;
        }
        //new key
        //reaching threshold, remove eldest key
        if (hm.size() == capacity) {
            //remove eldest key from key-value map
            hm.remove(head.next.key);
            //remove eldest node from dll
            head.next = head.next.next;
            //head.next.prev now is the second node
            head.next.prev = head;
        }
        //insert new key to end
        //crete new node for key
        Node insert = new Node(key, value);
        //define key-node in map
        hm.put(key, insert);
        //move node to tail
        moveToTail(insert);
    }
}
