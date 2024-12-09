package Combination;

import java.util.Scanner;

public class BOJ11051 {
    static long[][] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        D = new long[N+1][N+1];

        for (int i=0; i<=N; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }

        for (int i=2; i<=N; i++) {
            for (int j=1; j<i; j++) {
                D[i][j] = D[i-1][j-1] + D[i-1][j];
                // 모듈러 연산
                D[i][j] = D[i][j] % 10007;
            }
        }

        System.out.println(D[N][K]);
    }
}
