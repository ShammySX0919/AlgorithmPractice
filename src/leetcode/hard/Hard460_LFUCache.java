package leetcode.hard;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 *https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 *
 * Least Frequently Used (LFU) is a type of cache algorithm used to manage memory within a computer.
 * The standard characteristics of this method involve the system keeping track of the number of times
 * a block is referenced in memory. When the cache is full and requires more room the system will purge
 * the item with the lowest reference frequency.
 *Implementation:
 * Assign each block a counter, everytime it's used, increase its counter. when capacity reaches, swap out
 * the block with lowest counter(search and preorderFind it).
 *
 * Cons:
 * 1. does not reflect real situation well.
 * previously frequently used block might never be used again. but if they are accessed very frequently
 * in a short period and its counter goes up very high, it will less likely be swapped out even no longer useful.
 * 2. newer block since they have lower counter, likely be swapped out
 *
 * pure LFU system is fairly uncommon. hybrid solutions usually are used.
 *
 * Ehnancement: combine it with LRU to have LRFU to work better in more situations.
 *
 * Created by andrew on 26/11/16.
 */
//this is LFRU
public class Hard460_LFUCache {
    //doubly linked list of access count/frequency
    class DLLNode {
        public int count = 0;
        //keys of same frequency, the one at tail is most recently accessed, at head is least recently visited
        public LinkedHashSet<Integer> keys = null;
        //linking to higher and lower frequencies
        public DLLNode prev = null, next = null;

        public DLLNode(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
    //class variables
    private DLLNode head = null; //header of doubly linked list, which links frequency node from least to most frequently accessed.
    private int cap = 0;//capacity of cache
    //key-value. in this implementation, they are both Integers
    private HashMap<Integer, Integer> valueHash = null;
    //key-node, for O(1) node retrieval
    private HashMap<Integer, DLLNode> nodeHash = null;
    //initialize the class with capacity, as well as data structure for caching
    public Hard460_LFUCache(int capacity) {
        this.cap = capacity;
        valueHash = new HashMap<Integer, Integer>(capacity);
        nodeHash = new HashMap<Integer, DLLNode>();//I feel the frequency node should be well controlled
    }
    //since it's supported by hashmap, get is O(1)
    public int get(int key) {
        if (valueHash.containsKey(key)) {
            increaseCount(key);//every access increase its access frequency
            return valueHash.get(key);
        }
        return -1; //return -1 if key not existing
    }

    private void increaseCount(int key) {
        //O(1) to get the node, which has frequency information, as well as keys with same frequency. keys are arranged in their
        // access sequence. set of key is supported by linked hash set, so operation on key is also O(1)
        DLLNode node = nodeHash.get(key);
        //remove current key from its visit sequence, and it will be added back later to the end of set:most recent visited
        node.keys.remove(key);

        if (node.next == null) {
            //create a new frequency
            node.next = new DLLNode(node.count+1);
            node.next.prev = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count+1) {
            //adding to existing frequency
            node.next.keys.add(key);
        } else {
            //insert in the middle of this and its next
            DLLNode tmp = new DLLNode(node.count+1);
            tmp.keys.add(key);
            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }
        //update key-node map
        nodeHash.put(key, node.next);
        //remove node if this frequency is no longer useful so that DLL won't grow bigger and bigger with useless nodes
        if (node.keys.size() == 0) remove(node);
    }
    //remove useless frequency node
    private void remove(DLLNode node) {
        if (node.prev == null) {
            //current node is head, move head to its next
            head = node.next;
        } else {
            //remove this node by making left's right point to its right
            node.prev.next = node.next;
        }
        //adjust right neighbor's left if right neighbor exists
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }
    //set
    public void set(int key, int value) {
        if ( cap == 0 ) return;
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value);
        } else {
            //before adding new key, check capacity the first
            if (valueHash.size() < cap) {
                valueHash.put(key, value);
            } else {
                //if capacity has been reached, remove old content
                removeOld();
                //then adding new key
                valueHash.put(key, value);
            }
            //adding as head node since it is new
            addToHead(key);
        }
        //increase key's count
        increaseCount(key);
    }
    private void addToHead(int key) {
        if (head == null) {
            //first node
            head = new DLLNode(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            //head is not null, add node according to head's frequency
            //define new node's frequency is 0
            DLLNode node = new DLLNode(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            //head node's frequency is 0, so add this key in
            head.keys.add(key);
        }
        //map key to node
        nodeHash.put(key, head);
    }
    //remove eldest key that is in least frequency node
    private void removeOld() {
        if (head == null) return;
        //locate the key to remove, the eldest one in head, which is least frequent accessed
        int old = head.keys.iterator().next();
        //remove it from frequency node's key list
        head.keys.remove(old);
        //remove head if it's empty
        if (head.keys.size() == 0){
            remove(head);
        }
        //remove key from key-node map
        nodeHash.remove(old);
        //remove key from cache
        valueHash.remove(old);
    }
}
