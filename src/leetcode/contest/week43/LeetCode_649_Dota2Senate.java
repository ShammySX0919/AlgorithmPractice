package leetcode.contest.week43;

/**
 * Created by andrew on 7/29/2017.
 */
public class LeetCode_649_Dota2Senate {
    public static String predictPartyVictory(String senate) {
        StringBuilder sb = new StringBuilder();
        if (senate.length() == 1) {
            return ('R' == senate.charAt(0) ? "Radiant" : "Dire");
        }
        sb.append(senate.charAt(0));//sb hold previous senator who stays for next round

        int i = 1;
        while (true) {
            if(senate.length()==sb.length())break;
            if (i < senate.length()) {
                if (senate.charAt(i) == sb.charAt(sb.length() - 1)) {
                    sb.append(senate.charAt(i));
                    i++;//same party, adding it to senators for next round
                } else {
                    //differnt party, i is baned by i-1
                    //but next senator can be added for next round
                    if (i + 1 < senate.length()) {
                        sb.append(senate.charAt(i + 1));
                        i += 2;//i move to next
                    }
                     else if (i + 1 == senate.length() - 1) {
                            if (senate.charAt(i + 1) != senate.charAt(0)) {
                                sb.deleteCharAt(0);
                            }
                            senate = sb.toString();
                            sb.delete(0, sb.length());
                            sb.append(senate.charAt(0));
                            i = 1;
                            continue;
                        }

                    }
                }
        }
        return ('R' == sb.charAt(0) ? "Radiant" : "Dire");
    }
    public String predictPartyVictoryOther(String senate) {
        int r = 0, d = 0, start = 0;
        char[] arr = senate.toCharArray();
        for (char c : arr) {
            if (c == 'R') r++;
            else d++;
        }

        while (r > 0 && d > 0) {
            while (arr[start] != 'R' && arr[start] != 'D') {
                start = (start + 1) % arr.length;
            }

            char ban = 'R';
            if (arr[start] == 'R') {
                ban = 'D';
                d--;
            }
            else {
                r--;
            }
            int idx = (start + 1) % arr.length;
            while (arr[idx] != ban) {
                idx = (idx + 1) % arr.length;
            }

            arr[idx] = ' ';
            start = (start + 1) % arr.length;
        }

        return d == 0 ? "Radiant" : "Dire";
    }
    public static void main(String[] args){
        System.out.println(predictPartyVictory("DDRRR"));
    }
}
/*
649. Dota2 Senate

In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. Now the senate wants to make a decision about a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:

Ban one senator's right:
A senator can make another senator lose all his rights in this and all the following rounds.
Announce the victory:
If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and make the decision about the change in the game.
Given a string representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party respectively. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party, you need to predict which party will finally announce the victory and make the change in the Dota2 game. The output should be Radiant or Dire.

Example 1:
Input: "RD"
Output: "Radiant"
Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
And the second senator can't exercise any rights any more since his right has been banned.
And in the round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
Example 2:
Input: "RDD"
Output: "Dire"
Explanation:
The first senator comes from Radiant and he can just ban the next senator's right in the round 1.
And the second senator can't exercise any rights anymore since his right has been banned.
And the third senator comes from Dire and he can ban the first senator's right in the round 1.
And in the round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
Note:
The length of the given string will in the range [1, 10,000].

 */