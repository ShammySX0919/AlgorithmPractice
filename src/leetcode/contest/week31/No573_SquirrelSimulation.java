package leetcode.contest.week31;

/**
 * Created by andrew on 5/6/2017.
 */
public class No573_SquirrelSimulation {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0, maxDiff = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            //yes, I did this
            int dist = Math.abs(tree[0] - nut[0]) + Math.abs(tree[1] - nut[1]);
            sum += 2*dist;
            //I did similiar but minDiff
            //this considered both distance between nut and tree, nut and squirrel. it is not to find a nut that is closest to squirrel
            //it is to find one that saves most of distance
            maxDiff = Math.max(maxDiff, dist - Math.abs(squirrel[0] - nut[0]) - Math.abs(squirrel[1] - nut[1]));
        }
        return sum - maxDiff;
    }

    /**
     * this is more like what I came up, but with correct second loop
     * @param height
     * @param width
     * @param tree
     * @param squirrel
     * @param nuts
     * @return
     */
    public int minDistance2(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int n = nuts.length;
        int[] nutToTree = new int[n];
        int[] nutToSquirrel = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            nutToTree[i] = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
            sum += nutToTree[i] * 2;
            nutToSquirrel[i] = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int dist = sum + (nutToSquirrel[i] - nutToTree[i]);
            min = Math.min(min, dist);
        }

        return min;
    }
    public static void main(String... args){
        No573_SquirrelSimulation o = new No573_SquirrelSimulation();
        System.out.println(o.minDistance(5,7,new int[]{2,2},new int[]{4,4},new int[][]{{3,0},{2,5}}));
    }
}
