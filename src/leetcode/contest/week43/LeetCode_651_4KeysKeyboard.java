package leetcode.contest.week43;

/** http://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/
 * Created by andrew on 7/30/2017.
 */
public class LeetCode_651_4KeysKeyboard {
    public int maxA(int N) {
        // The optimal string length is N when N is smaller than 7
        if (N <= 6) return N;

        // An array to store result of subproblems
        int[] screen = new int[N];

        int b;  // To pick a breakpoint

        // Initializing the optimal lengths array for uptil 6 input
        // strokes.
        int n;
        for (n = 1; n <= 6; n++) screen[n - 1] = n;

        // Solve all subproblems in bottom manner
        for (n = 7; n <= N; n++) {
            // Initialize length of optimal string for n keystrokes
            screen[n - 1] = 0;

            // For any keystroke n, we need to loop from n-3 keystrokes
            // back to 1 keystroke to find a breakpoint 'b' after which we
            // will have ctrl-a, ctrl-c and then only ctrl-v all the way.
            for (b = n - 3; b >= 1; b--) {
                // if the breakpoint is at b'th keystroke then
                // the optimal string would have length
                // (n-b-1)*screen[b-1];
                int curr = (n - b - 1) * screen[b - 1];
                if (curr > screen[n - 1]) screen[n - 1] = curr;
            }
        }

        return screen[N - 1];
    }
}
/*
651. 4 Keys Keyboard

Imagine you have a special keyboard with the following keys:

Key 1: (A): Prints one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation:
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation:
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.
 */