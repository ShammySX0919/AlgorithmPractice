package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by andrew on 13/12/16.
 */
public class Hard146_LRUCache {
    //the cache:key-value
    private Map<Integer, Integer> map;

    public Hard146_LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }

    public int get(int key) {
        if(!map.containsKey(key)) { return -1; }
        return map.get(key);
    }

    public void set(int key, int value) {
        map.put(key,value);
    }

    private static class LinkedCappedHashMap<K,V> extends LinkedHashMap<K,V> {

        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            //true for access order, false for insertion order. other two parameters are arbitrarily given
            super(16, 0.9f, true);
            this.maximumCapacity = maximumCapacity;
        }

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > maximumCapacity;
        }
    }
}
