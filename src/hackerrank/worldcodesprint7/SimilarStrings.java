package hackerrank.worldcodesprint7;

import java.util.Scanner;

/**
 * Created by andrew on 26/09/16.
 */
public class SimilarStrings {
    private static void getAnswer(String s, int l, int r) {
        if (r == l) {
            System.out.println(s.length());
            return;
        }
        String ss = s.substring(l - 1, r);
        String ptn = getPattern(ss);
        int ans = 0;
        for (int i = 0; i < s.length() - (r - l); i++) {
            String ss2 = s.substring(i, i + (r - l + 1));
       /* if(ptn.equals(getPattern(ss2))){
            ans++;
        }*/
            if (isSamePattern(ptn, ss2)) {
                ans++;
            }

        }
        System.out.println(ans);
    }

    private static boolean isSamePattern(String ptn, String another) {
        int cnt = 0;
        for (int i = 0; i < another.length(); i++) {
            for (int j = 0; j < another.length(); j++) {
                if (Byte.valueOf("" + ptn.charAt(cnt)) != getFeature(another.charAt(i), another.charAt(j))) {
                    return false;//return earlier to same time
                }
                cnt++;

            }
        }
        return true;
    }

    private static String getPattern(String s) {
        if (s.length() == 1) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                sb.append(getFeature(s.charAt(i), s.charAt(j)));
            }
        }
        //System.out.println(s+":"+sb);
        return sb.toString();
    }

    private static byte getFeature(char c1, char c2) {
        if (c1 == c2) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        for (int i = 0; i < q; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            getAnswer(s, l, r);
            if (in.hasNextLine()) {
                in.nextLine();
            }
        }

        in.close();
    }
}