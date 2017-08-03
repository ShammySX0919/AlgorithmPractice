package leetcode.contest.week43;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by andrew on 7/30/2017.
 */
public class LeetCode_650_2KeysKeyboard {
    public int minSteps(int n) {

        if(n==1) return 0;
        if((n & 1) == 0)  //even
            return 2+minSteps(n/2);
        for(int i = 3; i * i <= n; i += 2){
            if (n % i == 0)
                return i+minSteps(n/i); // adding prime factors
        }
        return n; //prime number
    }
    //below is bfs solution
    class Stat {
        int currLen;
        int clipLen;
        public Stat (int a, int b) {
            currLen = a; clipLen = b;
        }
    }

    public int minStepsBFS(int n) {
        if (n == 1) return 0;

        int step = 1;
        Queue<Stat> queue = new LinkedList<>();
        queue.add(new Stat(1, 1));
        Set<String> set = new HashSet<>();
        set.add("1,1");

        while (true) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Stat s = queue.poll();
                // Copy All
                if (s.currLen != s.clipLen && !set.contains(s.currLen + "," + s.currLen)) {
                    queue.add(new Stat(s.currLen, s.currLen));
                    set.add(s.currLen + "," + s.currLen);
                }
                // Paste
                if (s.currLen + s.clipLen == n) return step;
                if (!set.contains((s.currLen + s.clipLen) + "," + s.clipLen) && s.currLen + s.clipLen < n) {
                    queue.add(new Stat(s.currLen + s.clipLen, s.clipLen));
                    set.add((s.currLen + s.clipLen) + "," + s.clipLen);
                }
            }
        }
    }
}
/*
650. 2 Keys Keyboard

Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Note:
The n will be in the range [1, 1000].
 */