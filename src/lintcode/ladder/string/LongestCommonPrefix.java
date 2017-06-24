package lintcode.ladder.string;

public class LongestCommonPrefix {
	//using trie tree for this is natural
	//but this simply checks common prefixes, it could be done easily by checking each letter in each string
	/**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0)return "";
        //O(n*m)
        boolean isContinue=true;
        int i=0;
        //for each possible positions
        for(;isContinue;i++){
            Character c=null;
            int cnt = 0;
            for(String s:strs){//check each string
                if(i>=s.length()){
                    isContinue=false;
                    break;
                }
                if(cnt==0){
                    c = s.charAt(i);
                }else{
                     if( c!=s.charAt(i)){//stop if difference is found
                         isContinue=false;
                         break;
                     }
                }
                //number of strings have been checked
                cnt++;
            }
            //cnt is total number of strings being checked so far
            if(cnt!=strs.length){
                break;
            }
        }
        return strs[0].substring(0,i);//because after inner break, counter increases another 1
    }
}
/*


Given k strings, find the longest common prefix (LCP).

Example

For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"

For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"

*/