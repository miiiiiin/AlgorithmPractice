package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//[[0,0,0],[0,1,0],[0,0,0]]
// 0은 이동 가능한 칸, 1은 갈 수 없는 칸,
public class LeetCode_UniquePaths2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] grid = new int[3][3];
        Arrays.fill(grid[0], 0);
        Arrays.fill(grid[1], 0);
        Arrays.fill(grid[2], 0);

        int result = uniquePathsWithObstacles(grid);
        System.out.println(result);
    }
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        obstacleGrid[1][1] = 1;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i=0; i<obstacleGrid.length; i++){
            for(int j=0; j<obstacleGrid[i].length; j++){
                // 방문 불가능
                if(obstacleGrid[i][j] == 1){
                    dp[i][j]=0;
                    System.out.println("방문불가능:" +  dp[i][j]);
                    continue;
                }

                if(i == 0 && j == 0) dp[i][j] = 1;
                else if (i==0) {
                    dp[i][j] = dp[i][j-1];
                    System.out.println("방문가능:" +  dp[i][j]);
                }
                else if (j==0) {
                    dp[i][j] = dp[i-1][j];
                    System.out.println("방문가능2:" +  dp[i][j]);
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    System.out.println("방문가능3:" +  dp[i][j]);
                }
            }
        }

        // m-1, n-1 리턴
        return dp[dp.length-1][dp[0].length-1];
    }
}


