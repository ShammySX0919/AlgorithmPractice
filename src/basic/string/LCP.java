package basic.string;

/**
 * find longest common prefix
 * Created by andrew on 19/10/16.
 */
public class LCP {
    public int getLCP(String s, String t){
        Objects.requireNonNull(s);
        Objects.requireNonNull(t);
        int len = Math.min(s.length(),t.length());
        for(int i=0;i<len;i++){
            if(s.chatAt(i)!=t.charAt(i))
                return i;
        }
        return len;
    }
}