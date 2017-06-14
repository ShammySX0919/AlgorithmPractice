package amazon.medium;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Andrew Ma on 5/2/2017.
 */
public class FirstNonRepeatWordInAStream {
    private Set<String> uniqueSet = new LinkedHashSet<>();
    private Set<String> nonUniqueSet = new HashSet<>();
    private String getNextWord(Stream input){
        if(!input.hasNext())return null;
        StringBuilder sb = new StringBuilder();
        while(input.hasNext()){
            char c = input.getNext();
            if((c==' '||c=='.')&&sb.length()>0){
               break;
            }
            else{
                if(c!=' ' && c!='.') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
    public  String firstWord(final Stream input) {
        String next = getNextWord(input);
        while(next!=null){
            if(nonUniqueSet.add(next)){
                uniqueSet.add(next);//add if new
            }else{
                //remove if repeat
                uniqueSet.remove(next);
            }
            next = getNextWord(input);
        }
        return nonUniqueSet.iterator().next();
    }

}

interface Stream <T> {
    char getNext();
    boolean hasNext();
}


