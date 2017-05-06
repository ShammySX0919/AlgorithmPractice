package leetcode.easy;

public class Easy459_RepeatedSubstringPattern {
	 //brute force solution the first
    //java's string.split is easy to use here
    public boolean repeatedSubstringPattern(String str) {
        if(str==null)return false;
        if(str.length()<=1)return false;
        //starting from b for 1 length substring
        for(int i=1;i<str.length()/2+1;i++){
            //only for the ones divisible length
            if(str.length()%i==0){//this is a key to pass
            //begin,end index, end is exclusive
                String[] tmp = str.split(str.substring(0,i));
                if(tmp.length==0)return true;
            }
        }
        return false;
    }
}
