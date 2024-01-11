package DP;

import java.util.Scanner;

public class LeetCode_UniquePath {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int result = uniquePaths(m, n);
        System.out.println(result);
    }
    public static int uniquePaths(int m, int n) {

        // dp 테이블 초기화
        int[][] dp = new int[m][n];

        // 첫 행, 첫 열 초기화 (기본 경우)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // (1, 1)부터 시작하므로 첫 행, 첫 열은 이미 초기화됨
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}