package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Draft432_AllO1Operations {
//by default, if you do not overwrite equals and hashcode, jvm uses object's address/pointer as value to 
//do the operation
	class KeyValuePair{
        String key;
        int val;
        public KeyValuePair(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    /** Initialize your data structure here. */
    HashMap<String, KeyValuePair> map;
    PriorityQueue<KeyValuePair> minQ;
    PriorityQueue<KeyValuePair> maxQ;
    
    public Draft432_AllO1Operations() {
        map = new HashMap<String, KeyValuePair>();
        
        minQ = new PriorityQueue<KeyValuePair>(new Comparator<KeyValuePair>(){
            public int compare(KeyValuePair a, KeyValuePair b) {
                return a.val - b.val;
            }
        });        
        
        maxQ = new PriorityQueue<KeyValuePair>(new Comparator<KeyValuePair>(){
            public int compare(KeyValuePair a, KeyValuePair b) {
                return b.val - a.val;
            }
        });
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            map.put(key, new KeyValuePair(key, 1));
            KeyValuePair node = map.get(key);
            minQ.add(node);
            maxQ.add(node);
        } else {
            KeyValuePair node = map.get(key);
            minQ.remove(node);
            maxQ.remove(node);
            node.val++;
            map.put(key, node);
            minQ.add(node);
            maxQ.add(node);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it one the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            KeyValuePair node = map.get(key);
            if (node.val == 1) {
                map.remove(key);
                minQ.remove(node);
                maxQ.remove(node);
            } else {
                minQ.remove(node);
                maxQ.remove(node);
                node.val--;
                map.put(key, node);
                minQ.add(node);
                maxQ.add(node);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return maxQ.isEmpty() ? "" : maxQ.peek().key;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return minQ.isEmpty() ? "" : minQ.peek().key;
    }
}