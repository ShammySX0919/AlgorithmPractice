package basic.trie;
/**
 * Unlike a binary search tree, no node in the tree stores the key associated with that node; 
 * instead, its position in the tree defines the key with which it is associated. 
 * All the descendants of a node have a common prefix of the string associated with that node, 
 * and the root is associated with the empty string.
 * Values are not necessarily associated with every node. Rather, values tend only to be associated with leaves, 
 * and with some inner nodes that correspond to keys of interest
 * 
 * Values are not necessarily associated with every node. Rather, values tend only to be associated with leaves, 
 * and with some inner nodes that correspond to keys of interest.
 * 
 * A trie can also be used to replace a hash table. it has no conlision, and it's worst case is O(m), m is length of key
 * 
 * quick on search, insert and delete
 * 
 * to save space, you can use compressed trie
 * 
 * suffix tree is a special kind of trie, it indexes all suffixes in a test in order to carry out fast full text search.
 * suffix tree: finding longest common substring. use lot more memory.
 * bitwise tries : CPU, cache
 * Common usage: predictive text or autcomplete dictionary.
 * 				spell checking, approximate matching algorithms
 * 
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;


	class TrieNode {
        char charValue;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLeaf = false;
        boolean isStopNode = false;
        int wordCnt = 0;//since everytime adding a , a character node will be touched, 
        //we can count number of words prefixed/below this node easily

        public TrieNode() {}

        public TrieNode(char c){
            this.charValue = c;
            wordCnt++;
        }
    }

	 class TrieTree {

		private static final int maxBranchCountLimit = 100;
		private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie. couter number of words a node belongs to at same time
         * @param value
         */
        public void insert(final String value) {
        	TrieNode currentNode = root;//root contains no character
        	//R-way tree
        	HashMap<Character, TrieNode> currentChildren = root.children;
            
            // We would skip empty and null input values
            for(int i=0; i<value.length(); i++){
                if (currentNode.isStopNode){
                    break;
                }

            	//this for loop is to go deeper and deeper instead of wider and wider
                char charValue = value.charAt(i);
                TrieNode nextNode;
                //next node is to go deeper
                //technique:within growth of ----->, each step actually goes deeper vertically
                if(currentChildren.containsKey(charValue)){
                    nextNode = currentChildren.get(charValue);
                    nextNode.wordCnt++;
                }else{
                    nextNode = new TrieNode(charValue);
                    currentChildren.put(charValue, nextNode);
                }

                //make current children the stop node
                if (currentChildren.size() > maxBranchCountLimit){
                    // Clean all the child nodes!
                    for (TrieNode child : currentChildren.values()){
                        child.children.clear();
                    }
                    // Then mark this node as stop node.
                    currentNode.isStopNode = true;
                    break;
                }
                currentNode = nextNode;
                currentChildren = currentNode.children;

                //set leaf node. mark it as an end of word
                if(i==value.length()-1){
                    nextNode.isLeaf = true;
                }
            }
        }

        /**
         * Extract the longest common prefix in a set of string values,
         * if there is no common prefix returns empty string.
         * This is from root to count
         * @return String containing a common prefix
         */
        public String getLongestCommonPrefix() {
            HashMap<Character, TrieNode> children = root.children;
            String prefix="";
            //on path of common prefix, children size is 1
            while (!children.isEmpty() && children.size() == 1) {
                // We check if there is only one child for current node in trie
                TrieNode currNode = null ;
                //this is in for loop, but loops only once since children.size()==1
                for (Map.Entry<Character, TrieNode> entry : children.entrySet()){
                    currNode = (TrieNode) entry.getValue();
                    prefix +=entry.getKey();
                }
                //can only go as far as shortest word
                if (currNode.isLeaf){break;}
                children = currNode.children;
            }
            return prefix;
        }
        //useless now
        public int getWordCount(TrieNode fromNode) {
        	Objects.requireNonNull(fromNode);
        	int cnt=0;
        	Queue<TrieNode> q = new LinkedList<TrieNode>();
        	q.add(fromNode);
        	//this is a tree, there's no cycle to worry
        	while(!q.isEmpty()){
        		TrieNode curNode = q.poll();
        		if(curNode.isLeaf)cnt++;
        		for(Map.Entry<Character, TrieNode> e:curNode.children.entrySet()){
        			q.add(e.getValue());
        		}
        	}
        	
        	return cnt;
        	
        }
        public int find(String partial){
        	TrieNode curNode = root;
        	
        	for(int i=0;i<partial.length();i++){
        		TrieNode nextNode = null;
        		for(Map.Entry<Character, TrieNode> e:curNode.children.entrySet()){
        			if(e.getKey()==partial.charAt(i)){
        				nextNode = e.getValue();
        				break;
        			}
        		}
        		if(nextNode==null){return 0;}//within search length, finding no more children
        		curNode= nextNode;
        	}
        	return curNode.wordCnt;
        }

	}

public class TriesContacts {
    static TrieTree trie = new TrieTree();
    public static void add(String name){
        trie.insert(name);
    }
    public static void find(String partial){
        System.out.println(trie.find(partial));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if("add".equals(op)){
                add(contact);
            }else if("preorderFind".equals(op)){
                find(contact);
            }
        }
    }
}