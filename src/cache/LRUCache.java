package cache;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.

 * Created by andrew on 5/4/2017.
 */

import java.util.HashMap;

/**
 * doubly linked list. quickly insert/remove nodes. and remember adding sequence
 */
class Node{
    int key,value;
    Node pre,next;
    public Node(int key,int value){
        this.key = key;
        this.value = value;
    }
}
public class LRUCache {
    int capacity;
    //key and node. using hashmap to quickly preorderFind nodes to operate
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    //head of LRU, most recently accessed node
    Node head=null;
    //tail of LRU. least recently accessed node. it will be removed when capacity reached
    Node end=null;
    //set up capacity
    public LRUCache(int capacity){
        this.capacity = capacity;
    }
//access cache, put the accessed node to head
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setAsHead(n);
            return n.value;
        }

        return -1;
    }
    public void remove(Node n){
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            head = n.next;
        }

        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            end = n.pre;
        }

    }

    public void setAsHead(Node n){
        n.next = head;
        n.pre = null;

        if(head!=null)
            head.pre = n;

        head = n;
        //first node, both head and end point to it
        if(end ==null)
            end = head;
    }
    //put key-value to cache. it should check capacity
    //when capacity is reached, the tail should be removed
    //the new key is put at head
    public void set(int key, int value) {
        //for existing key, put it to head
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setAsHead(old);
        }else{
            //for new key
            Node created = new Node(key, value);
            //check capacity
            if(map.size()>=capacity){
                //remove tail:tail is the least used node
                map.remove(end.key);
                remove(end);

            }
            setAsHead(created);
            //define new key-node in map
            map.put(key, created);
        }
    }
}
