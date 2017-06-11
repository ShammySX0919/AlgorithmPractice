package leetcode.contest.week36;

/**
 *string buffer method is out of memory.
 * Created by andrew on 6/10/2017.
 */
public class StringIterator {
    //maintain curIndex always pointing to letter
    int curIndex, offsetMax,offset;
    //StringBuffer sb = new StringBuffer();
    //int curIndex = 0;
    String str;
    public StringIterator(String compressedString) {
        str = compressedString;
        curIndex = 0;
        offsetMax = getOffsetMax();
        offset = 0;
    }

    private int getOffsetMax() {
        StringBuilder sb = new StringBuilder();
        int p = curIndex+1;

        while(p<str.length() && Character.isDigit(str.charAt(p))){
            sb.append(str.charAt(p));
            p++;
        }
        if(sb.length()==0)return 0;
        return Integer.parseInt(sb.toString());
    }

    public char next() {
        if(hasNext()) {
            char nextC = str.charAt(curIndex);
            offset++;
            if(offset==offsetMax){
                curIndex+=String.valueOf(offsetMax).length()+1;
                offsetMax = getOffsetMax();
                offset = 0;
            }
            return nextC;
        }
        else{
            return ' ';
        }

    }

    public boolean hasNext() {
        return offset<offsetMax;
    }
    public static void main(String[] args){
//        String str = "L1e2t1C1o1d1e1";
//        StringIterator o = new StringIterator(str);
//        System.out.println(o.next());
//        System.out.println(o.next());
//        System.out.println(o.next());
//        System.out.println(o.next());
//        System.out.println(o.next());
//        System.out.println(o.next());
//        System.out.println(o.hasNext());
//        System.out.println(o.next());
//        System.out.println(o.hasNext());
        String str = "x6";
        StringIterator o = new StringIterator(str);
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.hasNext());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.next());
        System.out.println(o.hasNext());
        System.out.println(o.next());
    }
}

/*
604. Design Compressed String Iterator

    User Accepted: 0
    User Tried: 0
    Total Accepted: 0
    Total Submissions: 0
    Difficulty: Easy

Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

 */