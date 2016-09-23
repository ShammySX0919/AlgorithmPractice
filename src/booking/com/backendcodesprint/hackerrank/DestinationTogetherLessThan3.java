package booking.com.backendcodesprint.hackerrank;

import java.util.Scanner;

/**
 * Created by andrew on 22/09/16.
 */
public class DestinationTogetherLessThan3 {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        in.close();
        int uniqueCities = (n - c) + (m - c) + c;
//common choice
        long result = 1;
        int cnt = uniqueCities - 1;
        while (cnt > 0) {
            result = result * cnt;
            cnt--;
        }

        System.out.println(result);
    }
}
/*
input
10 9 4
expected output
87178291200
 */