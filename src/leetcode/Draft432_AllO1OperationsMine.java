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
	public boolean equals(Node o){
		if(o==null)return false;
		return this.val==o.val;
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
				Node tmp = value.next;
				value.next=newValue;
				newValue.next=tmp;
				if(tail.val==value.val){//this could also be address based
					tail = newValue;
				}
				valueToKeys.put(newValue, new HashSet<String>());
			}
			//we might do dupliate operation on set, but this makes code shorter
			valueToKeys.get(newValue).add(key);
			//remove key from its old value's list
			valueToKeys.get(value).remove(key);
			//set key map to new value
			keyToValue.put(key, newValue);

		}
		else{
			//new key
			if(!valueToKeys.containsKey(value)){
				valueToKeys.put(value,new HashSet<String>());
				//new key's value is smallest
				Node tmp = head.next;
				head = value;
				value.next = tmp;
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
			//if value contains no more key, remove it
			if(valueToKeys.get(value).size()==0){
				valueToKeys.remove(value);
				Node p = value.prev;
				Node n = value.next;
				//list update
				if(p!=null){
					
					p.next=n;
				}
				if( n!=null){
					n.prev=p;
				}
				if(head==value){
					head = value.next;
				}
				if(tail==value){
					tail = value.prev;
				}

			}
			//deal with new value
			if(newValue.val==0){
				//if newValue is 0, then remove key
				keyToValue.remove(key);
			}else{
				keyToValue.put(key, newValue);
				if(keyToValue.containsKey(newValue)){
					valueToKeys.get(newValue).add(key);
				}else{
					//create new value and adding it to list and everywhere
					Node tmp = value.prev;
					if(tmp==null){
						head = newValue;
						newValue.next=value;
						value.prev=newValue;
					}else{
						value.prev=newValue;
						newValue.prev=tmp;
						tmp.next=newValue;
						newValue.next=value;
					}
					if(head.val==value.val){//this could also be address based
						head = newValue;
					}
					valueToKeys.put(newValue, new HashSet<String>());
					//we might do dupliate operation on set, but this makes code shorter
					valueToKeys.get(newValue).add(key);
				
				}
			}
		}		
	}

}
