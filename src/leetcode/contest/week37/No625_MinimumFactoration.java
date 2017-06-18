package leetcode.contest.week37;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * his problem is a little bit tricky.
 * Factors should be selected from 2 to 9 and should be as large as possible.

    Any 10 digit solution will overflow.
 * Created by andrew on 6/17/2017.
 */
public class No625_MinimumFactoration {
    public int smallestFactorization(int a) {
        int k = 9;
        List<Integer> ans = new ArrayList<>();
        //less than 10, itself
        if (a <= 9) return a;
        //get all the divisors that is between 2 and 9, because it only cares number between 2 and 9,
        //in order to make a number from single digits
        while (a > 1 && k >= 2) {
            if (a % k == 0) {
                ans.add(k);
                a = a / k;
            } else {
                k--;
            }
        }
        Collections.sort(ans);
        // Integer.MAX_VALUE = 2147483647
        // Note: ans starts at least with 2 (guaranteed to have overflow if the size is great or equal 10)
        if (a > 10 || ans.size() >= 10) return 0;
        int num = 0;
        for (int i : ans) {
            num *= 10;
            num += i;
        }
        return num;
    }
}

/*
Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48

Output:

68

Example 2
Input:

15

Output:

35
 */