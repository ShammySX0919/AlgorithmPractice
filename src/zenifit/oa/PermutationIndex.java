package zenifit.oa;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew on 6/25/2017.
 */
public class PermutationIndex {
    public long permutationIndexII(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        long res = 1;
        long factor = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = A.length - 1; i >= 0; i--) {
            int count = 0;
            Integer val = map.get(A[i]);
            if (val == null) {
                map.put(A[i], 1);
            } else {
                map.put(A[i], val + 1);
            }
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    count++;
                }
            }
            res += count * factor / duplicatesFactor(map);
            factor *= (A.length - i);
        }
        return res;
    }
    private long duplicatesFactor(Map<Integer, Integer> map) {
        long res = 1;
        for (int val : map.values()) {
            res *= getFactor(val);
        }
        return res;
    }
    private long getFactor(int num) {
        long res = 1;
        for (int i = 1; i <= num; i++) {
            res *= i;
        }
        return res;
    }
}
