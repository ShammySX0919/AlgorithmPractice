package basic.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TrieUtil {

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

		private static final int maxBranchCountLimit = 0;
		private TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         * @param value
         */
        public void insert(final String value) {
            HashMap<Character, TrieNode> currentChildren = root.children;
            TrieNode currentNode = root;
            // We would skip empty and null input values
            for(int i=0; i<value.length(); i++){
                char charValue = value.charAt(i);
                TrieNode nextNode;

                if(currentChildren.containsKey(charValue)){
                    nextNode = currentChildren.get(charValue);
                    
                }else{
                    nextNode = new TrieNode(charValue);
                    
                    currentChildren.put(charValue, nextNode);
                }

                if (currentNode.isStopNode){
                    break;
                }

                if (currentChildren.size() > maxBranchCountLimit){
                    // Clean all the child nodes!
                    for (TrieNode child : currentChildren.values()){
                        child.children.clear();
                    }
                    // Then mark this node as stop node.
                    currentNode.isStopNode = true;
                    break;
                }
                currentChildren = nextNode.children;
                currentNode = nextNode;

                //set leaf node
                if(i==value.length()-1){
                    nextNode.isLeaf = true;
                }
            }
        }

        /**
         * Extract the longest common prefix in a set of string values,
         * if there is no common prefix returns empty string.
         *
         * @return String containing a common prefix
         */
        public String getLongestCommonPrefix() {
            HashMap<Character, TrieNode> children = root.children;
            String prefix="";
            while (!children.isEmpty() && children.size() == 1) {
                // We check if there is only one child for current node in trie
                TrieNode t = null ;
                for (Map.Entry entry : children.entrySet()){
                    t = (TrieNode) entry.getValue();
                    prefix +=entry.getKey();
                }
                if (t.isLeaf){break;}
                children = t.children;
            }
            return prefix;
        }

	}        
}
