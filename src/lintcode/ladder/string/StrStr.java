package lintcode.ladder.string;

public class StrStr {
	/**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        if(source==null||target==null){return -1;}
        if(source.length()==0&&target.length()==0){return 0;}
        else if(source.length()==0&&target.length()>0){return -1;}
        else if(source.length()>0 && target.length()==0){return -1;}
        //an easy java function
        //return source.indexOf(target);
        //for each position in source, up to s.n-t.n
        for(int i=0;i<source.length()-target.length()+1;i++){
            //for each position in target
            for(int j=0;j<target.length();j++){
            	//compare each char in target with relative position in source starting with i
                if(source.charAt(i+j)!=target.charAt(j)){
                    break;
                }
                if(j==target.length()-1)return i;
            }
            
        }
        return -1;
    }
}
/*


For a given source string and a target string, you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.
Have you met this question in a real interview?
Clarification

Do I need to implement KMP Algorithm in a real interview?

    Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation ability. But make sure your confirm with the interviewer first.

Example

If source = "source" and target = "target", return -1.

If source = "abcdabcdefg" and target = "bcd", return 1.

*/