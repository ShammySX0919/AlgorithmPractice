package leetcode.hard;

import java.util.Arrays;

/**
 * Created by 212595974 on 5/1/2017.
 */
public class MaximumVacation {

    int max = 0, N = 0, K = 0;
//dfs is easy to understand, it does not check duplicate visit because it allows. it stops at known stop level.
    //O(N^K) time limit exceed
    public int maxVacationDaysDfs(int[][] flights, int[][] days) {
        N = flights.length;
        K = days[0].length;
        dfs(flights, days, 0, 0, 0);

        return max;
    }

    /**
     * week is increasing
     * sum is accumulating with current week data
     * @param f
     * @param d
     * @param curr
     * @param week
     * @param sum
     */
    private void dfs(int[][] f, int[][] d, int curr, int week, int sum) {
        if (week == K) {//only process certain weeks
            max = Math.max(max, sum);
            return;
        }
        //every time, check every city
        for (int dest = 0; dest < N; dest++) {
            //only for likely cities, self or neighbors
            if (curr == dest || f[curr][dest] == 1) {
                dfs(f, d, dest, week + 1, sum + d[dest][week]);
            }
        }
    }
//not working
    public static int maxVacationDays(int[][] flightBetweenCities,int[][] cityWeeklyVacation){
        //dp[w][c] is maximum vacation in week w and select staying in city c
        int weeks = cityWeeklyVacation[0].length;
        int cities = flightBetweenCities.length;
        int dp[][] = new int[weeks][cities];
        int maxVac = 0;
        //set up first week values for each city as start point
          for(int city = 0;city<cities;city++) {
              if (city == 0 || flightBetweenCities[0][city] == 1) {
                  //supposing staying in city 0 for all weeks
                  dp[0][city] = cityWeeklyVacation[city][0];
              }
          }
          //for each week, probe option for each city
        for(int week=1;week<weeks;week++){
            for(int city=0;city<cities;city++){

                for(int tCity=0;tCity<cities;tCity++){//tCity is city's next level neighbor
                    //for the cities it can stay in the checking week. either stay in same city or flying to another city
                    if((tCity==city||flightBetweenCities[city][tCity]==1)){
                        //reachable city will now no longer negative
                        dp[week][tCity] = Math.max(dp[week][tCity],dp[week-1][city]+cityWeeklyVacation[tCity][week]);
                        maxVac = Math.max(maxVac,dp[week][tCity]);
                    }
                }
            }
        }
        return maxVac;
    }
//method 3, change dp[][] to dp[] since it is just week related
public static int maxVacationDays1D(int[][] flights, int[][] days) {
    int N = flights.length;
    int K = days[0].length;
    int[] dp = new int[N];
    Arrays.fill(dp, Integer.MIN_VALUE);
    dp[0] = 0;//dp is layer by layer reused. each layer is a week's data

    for (int i = 0; i < K; i++) {
        int[] temp = new int[N];//current layer's dp
        Arrays.fill(temp, Integer.MIN_VALUE);
        for (int j = 0; j < N; j++) {
            for(int k = 0; k < N; k++) {
                if (j == k || flights[k][j] == 1) {
                    //last layer's dp
                    temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
                }
            }
        }
        dp = temp;
    }

    int max = 0;
    for (int v : dp) {
        max = Math.max(max, v);
    }

    return max;
}
//method 4, backword dp
public int maxVacationDaysBackward(int[][] flights, int[][] days) {
    if (days.length == 0 || flights.length == 0) return 0;
    int Cities = flights.length;
    int Weeks = days[0].length;
    int[][] vocationDays = new int[Cities][Weeks + 1];
    for (int w = Weeks - 1; w >= 0; w--) {
        for (int c = 0; c < Cities; c++) {
            vocationDays[c][w] = days[c][w] + vocationDays[c][w + 1];
            for (int j = 0; j < Cities; j++) {
                if (flights[c][j] == 1) {
                    vocationDays[c][w] = Math.max(days[j][w] + vocationDays[j][w + 1], vocationDays[c][w]);
                }
            }
        }
    }
    return vocationDays[0][0];
}
    public static void main(String... args){
        int[][] cityConnections = null;
        cityConnections = new int[][]{{0,1,1},{1,0,1},{1,1,0}};
        int[][] cityVac = null;
        cityVac = new int[][]{{7,0,0},{0,7,0},{0,0,7}};
        System.out.println(MaximumVacation.maxVacationDays1D(cityConnections,cityVac));

        cityConnections = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        cityVac = new int[][]{{1,1,1},{7,7,7},{7,7,7}};
        System.out.println(MaximumVacation.maxVacationDays1D(cityConnections,cityVac));
        cityConnections = new int[][]{{0,1,1},{1,0,1},{1,1,0}};
        cityVac = new int[][]{{1,3,1},{6,0,3},{3,3,3}};
        System.out.println(MaximumVacation.maxVacationDays1D(cityConnections,cityVac));
    }

}
