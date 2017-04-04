package basic.string;

/**
 * Created by 212595974 on 4/3/2017.
 */
public class AStringIsSubSequenceOfAnother {
    public static boolean isSubSequence(String aString,String anotherString){
        return isSubSequenceRec(aString,anotherString,aString.length(),anotherString.length());
    }
    private static boolean isSubSequenceRec(String aString,String anotherString, int len1, int len2){
        if(len1==0)return true;
        if(len2==0) return false;//ind1 is not aString's end, another has exhausted
        if(aString.charAt(len1-1)==anotherString.charAt(len2-1))
            return isSubSequenceRec(aString,anotherString,len1-1,len2-1);
        //otherwise, make anotherString shorter. this is because characters in aString has to be match
        return isSubSequenceRec(aString,anotherString,len1,len2-1);
    }
    public static boolean isSubSequenceIter(String aString,String another){
        int j=0;
        for(int i=0;i<another.length()&j<aString.length();i++){
            //search in another one by one
            if(aString.charAt(j)==another.charAt(i))
                j++;
        }
        return aString.length()==j;
    }
    public static void main(String... args){
        System.out.println(true == AStringIsSubSequenceOfAnother.isSubSequence("abc","sdsajkjkjbkkkkckllll"));
        System.out.println(false == AStringIsSubSequenceOfAnother.isSubSequence("abc","sdsajkjkjbkkkkkllll"));
        System.out.println(true == AStringIsSubSequenceOfAnother.isSubSequenceIter("abc","sdsajkjkjbkkkkckllll"));
        System.out.println(false == AStringIsSubSequenceOfAnother.isSubSequenceIter("abc","sdsajkjkjbkkkkkllll"));
    }
}
