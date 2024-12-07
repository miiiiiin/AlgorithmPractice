package Combination;

import java.util.Scanner;

public class BOJ1010 {
    static int[][] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        D = new int[31][31];

        for (int i=0; i<=30; i++) {
            // 테이블 초기화
            D[i][0] = 1;
            D[i][i] = 1;
            D[i][1] = i;
        }

        for (int i=2; i<=30; i++) {
            for (int j=1; j<=i; j++) {
                D[i][j] = D[i-1][j-1] + D[i-1][j];
            }
        }

        for (int t=0; t<T; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            System.out.println(D[M][N]);
        }
    }
}
