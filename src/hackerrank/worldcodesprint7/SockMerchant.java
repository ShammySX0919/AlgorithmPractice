package hackerrank.worldcodesprint7;

import java.util.Scanner;

/**
 * Created by andrew on 26/09/16.
 */
public class SockMerchant {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c[] = new int[n];
        for (int c_i = 0; c_i < n; c_i++) {
            c[c_i] = in.nextInt();
        }
        int cnt[] = new int[101];
        for (int i = 0; i < c.length; i++) {
            cnt[c[i]]++;
        }

        int result = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 1) {
                //System.out.println(i+":"+cnt[i]);
                result += cnt[i] / 2;
            }
        }
        System.out.println(result);
    }
}
