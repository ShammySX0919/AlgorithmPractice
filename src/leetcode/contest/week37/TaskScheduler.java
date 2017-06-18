package leetcode.contest.week37;

import java.util.*;

/**
 * Created by andrew on 6/17/2017.
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        Map<Character, Integer> taskToCount = new HashMap<>();
        //task count
        for (char c : tasks) {
            taskToCount.put(c, taskToCount.getOrDefault(c, 0) + 1);
        }
        //min heap
        Queue<Integer> queue = new PriorityQueue<>((i1, i2) -> i2 - i1);
        //put task counts into pq
        for (char c : taskToCount.keySet()) queue.offer(taskToCount.get(c));

        Map<Integer, Integer> coolDown = new HashMap<>();

        int currTime = 0;
        while (!queue.isEmpty() || !coolDown.isEmpty()) {
            if (coolDown.containsKey(currTime - n - 1)) {
                queue.offer(coolDown.remove(currTime - n - 1));
            }
            if (!queue.isEmpty()) {
                int left = queue.poll() - 1;
                if (left != 0) coolDown.put(currTime, left);
            }
            currTime++;
        }

        return currTime;
    }
    public int leastInterval2(char[] tasks, int n) {

        if(tasks.length == 0) return 0;
        if(n == 0) return tasks.length;

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }
}

/*
621. Task Scheduler

    User Accepted: 213
    User Tried: 384
    Total Accepted: 218
    Total Submissions: 833
    Difficulty: Medium

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks,
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:

Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

Note:

    The number of tasks is in the range [1, 10000].

 */
