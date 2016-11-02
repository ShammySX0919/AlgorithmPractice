package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Draft432_AllO1OperationsMine {
//no need to implement comparator. practice purpose	
class Node implements Comparator<Node>{
	Node next,prev;//doubly linked list
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
	@Override
	public int compare(Node o1, Node o2) {
		Objects.requireNonNull(o1);
		Objects.requireNonNull(o2);
		return o1.val - o2.val;
	}
}
/**
 * thoughts:
 * 
 * maintain a key-value map for O(1) key finding, as well as value-keys mapping for O(1)
 * value finding. Values are supported by doubly linked list for 
 * O(1) access to min and max. order is maintained during ins and dec operation.
 */

	Map<String,Node> keyToValue = new HashMap<String,Node>();
	Map<Node,Set<String>> valueToKeys = new HashMap<Node,Set<String>>();
	Node head=null,tail=null;

    public String getMaxKey() {
    	return tail==null?"": valueToKeys.get(tail).iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
    	return head==null ? "" : valueToKeys.get(head).iterator().next();
    }
    private void addNewBiggerValue(Node value,Node newValue){
    	//Node newValue = new Node(value.val+1);
    	Node next = value.next;
    	value.next=newValue;
    	newValue.prev=value;
    	newValue.next = next;
    	if(next!=null)next.prev=newValue;
    	else tail=newValue;
    	valueToKeys.put(newValue, new HashSet<String>());
    }
    private void addNewSmallerValue(Node value,Node newValue){
    	//Node newValue = new Node(value.val+1);
    	Node prev = value.prev;
    	value.prev=newValue;
    	newValue.next=value;
    	newValue.prev = prev;
    	if(prev!=null)prev.next=newValue;
    	else head=newValue;
    	valueToKeys.put(newValue, new HashSet<String>());
    }
    private void removeValue(Node value){
    	if(value==null)return;
    	//only remove nodes having no keys
    	if(valueToKeys.get(value)!=null && valueToKeys.get(value).size()>0)return;
    	valueToKeys.remove(value);
    	Node prev = value.prev;
    	Node next = value.next;
    	if(head==value)head=next;
    	if(tail==value)tail=prev;
    	if(prev!=null)prev.next=next;
    	if(next!=null)next.prev=prev;
    }
    private void removeKey(String key){
    	
    }
	public void inc(String key) {
		Node value = new Node(1);
		if(keyToValue.isEmpty()){
			//first ever key-value
			//set head and tail in doubly linked list
			head = value;
			tail= value;
			//define key-value map
			keyToValue.put(key, value);
			//define value-keys map
			Set<String> valueKeys = new HashSet<String>();
			valueKeys.add(key);
			valueToKeys.put(value, valueKeys);
		}
		else if(keyToValue.containsKey(key)){
		//increase val by 1
			value = keyToValue.get(key);
			Node newValue = new Node(value.val+1);
			//prepare newValue in doubly linked list
			if(!valueToKeys.containsKey(newValue)){
				//new value, insert it to value's next position
				//this way we maintain the order
				addNewBiggerValue(value,newValue);
				
			}
			//we might do dupliate operation on set, but this makes code shorter
			valueToKeys.get(newValue).add(key);
			//remove key from its old value's list
			valueToKeys.get(value).remove(key);
			removeValue(value);//try to remove value if value's key is empty
			//set key map to new value
			keyToValue.put(key, newValue);

		}
		else{
			//new key
			if(!valueToKeys.containsKey(value)){
				valueToKeys.put(value,new HashSet<String>());
				//new key's value is smallest
				value.next=head;
				head.prev=value;
				head = value;
				
			}
			valueToKeys.get(value).add(key);
			keyToValue.put(key, value);
		}
	}
	public void dec(String key) {
		//do it only when key is already defined
		if(keyToValue.containsKey(key)){
			Node value = keyToValue.get(key);
			Node newValue = new Node(value.val  -1);
			//1. removing key from value
			valueToKeys.get(value).remove(key);
			removeValue(value);//value will be possibly removed
			//if value contains no more key, remove it
			//deal with new value
			if(newValue.val==0){
				//if newValue is 0, then remove key
				keyToValue.remove(key);
			}else{
				keyToValue.put(key, newValue);
				if(valueToKeys.containsKey(newValue)){
					valueToKeys.get(newValue).add(key);
				}else{
					//create new value and adding it to list and everywhere
					addNewSmallerValue(value,newValue);
					valueToKeys.get(newValue).add(key);
				}
			}
		}		
	}
public static void main(String[] args){
	Draft432_AllO1OperationsMine o = new Draft432_AllO1OperationsMine();
	/*
	o.inc("hello");
	o.inc("goodbye");
	o.inc("hello");
	o.inc("hello");
	System.out.println(o.getMaxKey());
	o.inc("leet");
	o.inc("code");
	o.inc("leet");
	o.dec("hello");
	o.inc("leet");
	o.inc("code");
	o.inc("code");
	System.out.println(o.getMaxKey());
	*/
	o.inc("hello");
	o.dec("hello");;

	System.out.println(o.getMaxKey());
}
}
