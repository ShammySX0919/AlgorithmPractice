package hackerrank.worldcodesprint7;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andrew on 26/09/16.
 */
public class TwoCharacters {
    private static boolean checkValid(String s, char c1, char c2) {
        char next = s.charAt(0);
        if (next != c1 && next != c2) return false;
        for (char c : s.toCharArray()) {
            if (c != next) return false;
            next = (next == c1 ? c2 : c1);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        in.close();
        if (s.length() < 2) {
            System.out.println(0);
            return;
        }
        Set<Character> uniqueCharacters = new TreeSet<Character>();
        for (char c : s.toCharArray()) {
            uniqueCharacters.add(c);
        }
        if (uniqueCharacters.size() < 2) {
            System.out.println(0);
            return;
        } else if (uniqueCharacters.size() == 2) {
            char c1 = uniqueCharacters.iterator().next();
            uniqueCharacters.remove(c1);
            char c2 = uniqueCharacters.iterator().next();
            if (checkValid(s, c1, c2)) {
                System.out.println(s.length());
                return;
            }
        }
        Set<Character> set2 = new TreeSet<Character>(uniqueCharacters);
        int maxLength = 0;
        for (Character c1 : uniqueCharacters) {
            for (Character c2 : set2) {
                if (!c1.equals(c2)) {
                    //String s2 = s;
                    String ptRemove = "[^" + c1 + c2 + "]";
                    Pattern p = Pattern.compile(ptRemove);
                    //  get a matcher object
                    Matcher m = p.matcher(s);
                    if (m.find()) {
                        String newString = m.replaceAll("");
                        //intend to still use pattern match to validate the new string,
                        //but failed due to not familiar with it
                        //then use a home made method for it
                        if (newString.length() > maxLength && checkValid(newString, c1, c2)) {
                            maxLength = newString.length();
                        }
                    }
                }
            }
        }
        System.out.println(maxLength);
    }
}
