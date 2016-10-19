package basic.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieUtil {
/**
 * TrieNode 
 * is basically a character, and its children.
 * With some more attributes to facilitate operations
 * a flag of isLeaf and isStopNode
 * stop node reflects certain restriction we want to put on a trie tree, such as maximum deepth it supports
 * Children is implemented as a map of TrieNode mapping to its character
 * @author Andrew Ma
 *
 */
	class TrieNode {
        char charValue;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLeaf = false;
        boolean isStopNode = false;

        public TrieNode() {}

        public TrieNode(char c){
            this.charValue = c;
        }
    }

	public class TrieTree {

		private static final int maxBranchCountLimit = 100;
		private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
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

	}
	public static void main(String[] args){
		TrieUtil tu = new TrieUtil();
		TrieUtil.TrieTree tt =  tu.new TrieTree();
		tt.insert("Rose");
		tt.insert("Rosemary");
		tt.insert("Andy");
		tt.insert("Anddrew");
		System.out.println(tt.getLongestCommonPrefix());
	}
}
