package leetcode.hard;

/**
 * Created by andrew on 26/10/16.
 */
public class Hard440_KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        //starting from 1, you need to move k-1 steps to find the target
        int curr = 1;
        k = k - 1;//so k is k-1 after going through 1 (curr=1)
        while (k > 0) {
            //find out count of numbers between curr and curr+1
            //e.g. between 1 and 2, under the context of n, how many numbers are in between 1 and 2 in lexicographical order
            int steps = calSteps(n, curr, curr + 1);
            //if the steps/number of numbers between curr and curr+1 is smaller than k, we move to next single number
            if (steps <= k) {
                curr += 1;//move to next index number(1-9)
                k -= steps;//and deduct steps from k
            } else {
                //if steps more than needed between curr and curr+1, it is between curr and curr+1
                //then it needs to go to next level to check between curr*10 and (curr+1)*10
                //when curr goes to curr*10, it just uses 1 number so k minus 1 (with denary tree in mind)
                curr *= 10;
                k -= 1;
        }
    }
        return curr;
    }

    //use long in case of overflow

    /**
     * find out how many numbers are between n1 and n2 under context of n in lexicographical order.
     * for example, if n is 99, imaging a denary tree,
     * betweeen 1 and 2, there would be all numbers starting with 1
     * 10-19, 10numbers
     * 100 is greater than n, so it is not reaching to there
     * between 2 and 3, there are all numbers starting with 2
     * 20--29, 10 numbers
     * 200 is bigger tha 100, so it is not reaching to there
     *
     * @param n
     * @param n1
     * @param n2
     * @return
     */
    public int calSteps(int n, long n1, long n2) {
        int steps = 0;//step is number of numbers between n1 and n2 under context of n
        //keep growing n1 times each loop, until it's greater than n
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            //go to next layer
            n1 *= 10;
            n2 *= 10;
            //then you will understand why next loop it should look into min(n,n2)
        }
        return steps;
    }
    //try to write brute force code to understand it.
    int steps=0;//expect to go forward k steps
    int lastNode = 1;
    public int findKthNumber2(int n, int k) {
        //first level numbers, total 9. they are the valid start digit of numbers
        steps=0;
        lastNode =1;
        for(int i=1;steps<k && i<=9;i++) {
           preOrderDFS(n, k, i);
        }
        return lastNode;
    }
    //returns number it stops at
    void preOrderDFS(int n, int k, int curNode){
        if(steps>k)return ;
        //a valid step
        lastNode = curNode;
        //count it.
        steps++;//count it after marking/using it
        //next node in next level
        for(int i=curNode*10;steps<k && i<=n && i<=curNode*10+9;i++){
            preOrderDFS(n, k, i);
        }
    }

    public static void main(String[] args){
        //1,10,11,12,13,2,3,4,5,6,7,8,9
        Hard440_KthSmallestInLexicographicalOrder   o = new Hard440_KthSmallestInLexicographicalOrder();
//        System.out.println(o.findKthNumber2(100,10));
//        System.out.println(o.findKthNumber2(13,2));
        System.out.println(o.findKthNumber2(681692778,351251360));

    }
}