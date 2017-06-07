package amazon.medium;

import java.util.ArrayList;
import java.util.List;

/**
 *

 Ugly number is a number that only have PRIME factors 2, 3 and 5.

 Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
 Notice

 Note that 1 is typically treated as an ugly number.

Analysis:
 beside 1, there are three lists, 
    one is 2*u: u is an existing smaller ugly number
    second is 3*u
    third is 5*u
 This is to merge the three sorted list, until it reaches element of n
 
 * Created by 212595974 on 6/6/2017.
 */
public class UglyNumberII {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        List<Integer> uglys = new ArrayList<Integer>();
        uglys.add(1);

        int l2 = 0, l3 = 0, l5 = 0;
        // l2, l3 & l5 represents is a position has been multiplied with 2,3 or 5 yet
        //they grow from 0 step by step

        for (int i = 1; i < n; i++) {
            int lastNumber = uglys.get(i - 1);
            //every previous ugly number has a chance to multiply 2, 3 and 5
            while (uglys.get(l2) * 2 <= lastNumber) l2++;
            while (uglys.get(l3) * 3 <= lastNumber) l3++;
            while (uglys.get(l5) * 5 <= lastNumber) l5++;
            //pick the min one from three numbers that are greater or equals to last number
            uglys.add(Math.min(
                    Math.min(uglys.get(l2) * 2, uglys.get(l3) * 3),
                    uglys.get(l5) * 5
            ));
        }

        return uglys.get(n - 1);
    }
}
