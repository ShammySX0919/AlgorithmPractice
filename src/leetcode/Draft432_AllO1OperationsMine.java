package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 * use map to access value in O(1) time, use doubly linked list to access tail and head in O(1) time.
 * need to maintain the order of values in efficient O(1) time and that's supported by doubly linked list and map
 * for reverse searching form value to keys, it can have a list in value to track keys, but in order to
 * also easily achieve O(1) operation, I just use a map inside value to track the key, value of map is key itself.
 * 
 * since adding new value only considers adjacent value which can be +1 or -1, so accessing other nodes in this
 * task is O(1), and it does not need to have separate map to randomly access value in O(1) manner.
 * 
 * @author Andrew Ma
 *
 */
public class Draft432_AllO1OperationsMine {
	class Node{
		//key-key map for O(1) key retrieval
		Map<String,String> keys = new HashMap<String,String>();
		Node next,prev;//doubly linked list to organize nodes in order and O(1) tail, head access
		int val;
		public Node(int v){
			val = v;
		}
		public int hashCode(){
			return val;
		}
		//change it not using pointer
		public boolean equals(Object o){
			if(o==null)return false;
			return this.val==((Node)o).val;
		}
		public String getFirstKey(){
			return keys.keySet().iterator().next();
		}
		public void removeKey(String key){
			keys.remove(key);
		}
		public void addKey(String key){
			keys.put(key, key);
		}
		public boolean hasKey(){
			return keys.size()>0;
		}

	}
//global variables
		Map<String,Node> keyToValue = new HashMap<String,Node>();
		//found it's easier to use dummy nodes in linked list
		//be caution to not let list pointing back to tail and head in this implementation
		Node head=new Node(Integer.MIN_VALUE),tail=new Node(Integer.MAX_VALUE);

	    public String getMaxKey() {
	    	return tail.prev==null?"": tail.prev.getFirstKey();
	    }
	    
	    /** Returns one of the keys with Minimal value. */
	    public String getMinKey() {
	    	return head.next==null?"": head.next.getFirstKey();
	    }
	    private void addNewBiggerValue(String key,Node value){
	    	int nextValue = value.val+1;
	    	Node newValue = null;
	    	//value is tail, append new value
	    	if(value.next==null){
	    		newValue = new Node(nextValue);
	    		value.next = newValue;
	    		newValue.prev = value;
	    		tail.prev=newValue;
	    		newValue.addKey(key);

	    		//remove value if no key uses it
	    	}else if (value.next.val==nextValue){
	    		//next value is already there
	    		newValue = value.next;
	    		newValue.addKey(key);
	    	}
	    	else{
	    		//new value need to be inserted between value and its next
	    		Node next = value.next;
	    		newValue = new Node(nextValue);
	    		newValue.addKey(key);
	    		value.next = newValue;
	    		newValue.prev = value;
	    		newValue.next = next;
	    		next.prev=newValue;
	    	}
	    	//process old key value
    		keyToValue.put(key, newValue);
    		value.removeKey(key);
	    	if(!value.hasKey()){
    			removeValue(value);
    		}

	    }
	    private void addNewSmallerValue(String key,Node value){
	    	int prevValue = value.val-1;
	    	Node newValue = null;
	    	//value is tail, append new value
	    	if(prevValue==0){
	    		//remove key, after key is removed, value is still in linked list
	    		keyToValue.remove(key);
	    	}
	    	else if(value.prev==null){
	    		newValue = new Node(prevValue);
	    		value.prev = newValue;
	    		newValue.next = value;
	    		head.next=newValue;
	    		newValue.addKey(key);
	    	}else if (value.prev.val==prevValue){
	    		//next value is already there
	    		newValue = value.prev;
	    		newValue.addKey(key);
	    	}
	    	else{
	    		//new value need to be inserted between value and its next
	    		Node prev = value.prev;
	    		newValue = new Node(prevValue);
	    		newValue.addKey(key);
	    		value.prev = newValue;
	    		newValue.next = value;
	    		newValue.prev = prev;
	    		prev.next=newValue;
	    	}
	    	//process old key value
	    	if(newValue!=null){
	    		keyToValue.put(key, newValue);
	    	}
    		value.removeKey(key);
	    	if(!value.hasKey()){
    			removeValue(value);
    		}

	    }
	    //dealing with removing value from linked list, map operation have been taken cared of
	    //nodes in list do not point to head and tail
	    private void removeValue(Node value){
	    	if(value==null)return;
	    	Node prev = value.prev;
	    	Node next = value.next;
	    	if(prev==null){
	    		head.next = next;
	    	}else{
	    		prev.next = next;
	    	}
	    	if(next==null){
	    		tail.prev=prev;
	    	}else{
	    		next.prev = prev;
	    	}
	    }
	    
		public void inc(String key) {
			if(keyToValue.containsKey(key)){
				Node value = keyToValue.get(key);
				addNewBiggerValue(key, value);
			}
			else{
				//new key
				Node value = new Node(1);
				if(head.next==null){
					head.next=value;
					tail.prev=value;
				}else{
					//head contains value node
					if(head.next.val==value.val){
						value = head.next;

					}else{
						//new node
						value.next=head.next;
						head.next.prev=value;
						head.next=value;
					}
				}
				keyToValue.put(key, value);
				value.addKey(key);
			}
		}
		public void dec(String key) {

			if(keyToValue.containsKey(key)){
				Node value = keyToValue.get(key);
				addNewSmallerValue(key,value);
			}else{
				//invalid key, ignore
			}
		}
	    public static void main(String[] args){
	    	Draft432_AllO1OperationsMine o = new Draft432_AllO1OperationsMine();
	    	Scanner in = new Scanner(System.in);
	    	List<String> instructions=new ArrayList<String>();
	    	List<String> argus=new ArrayList<String>();
	    	String line1 = in.nextLine();
	    	String line2 = in.nextLine();
	    	for(String s:line1.split(",")){
	    		instructions.add(s);
	    	}
	    	for(String s:line2.split(",")){
	    		argus.add(s);
	    	}
	    	for(int i=1;i<instructions.size();i++){
	    		if("\"inc\"".equals(instructions.get(i))){
	    			o.inc(argus.get(i));
	    		}else if("\"dec\"".equals(instructions.get(i))){
	    			o.dec(argus.get(i));
	    		}else if("\"getMinKey\"".equals(instructions.get(i))){
	    			System.out.println(o.getMinKey());
	    		}
	    		else if("\"getMaxKey\"".equals(instructions.get(i))){
	    			System.out.println(o.getMaxKey());
	    		}
	    	}
	    }
	}

