package triplebyte;

/**
 * Created by 212595974 on 6/27/2017.
 */
public class InterleavingTwoStrings {
    public static String interleave(String str1, String str2){
        if(str1==null||str1.isEmpty())return str2;
        if(str2==null||str2.isEmpty())return str1;
        String longer = str1.length()>=str2.length()?str1:str2;
        String shorter = str1.length()>=str2.length()?str2:str1;
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(;i<shorter.length();i++){
           sb.append(longer.charAt(i)).append(shorter.charAt(i));
        }
        for(;i<longer.length();i++)
            sb.append(longer.charAt(i));
        return sb.toString();
    }
    public static void main(String args[]){
        System.out.println(interleave("abcd","123"));
        System.out.println(interleave("abcd","1234"));
        System.out.println(interleave("abcd","12345"));
    }
}
