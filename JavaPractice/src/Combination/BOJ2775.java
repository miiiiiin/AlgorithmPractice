package Combination;

import java.util.Scanner;

public class BOJ2775 {
    static int[][] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        D = new int[15][15];

        for (int i=0; i<15; i++) {
            // 점화식 테이블 초기화
            // i층의 1호실 값은 1
            D[i][1] = 1;
            // 0층의 i호의 수는 i로 초기화
            D[0][i] = i;
        }

        for (int i=1; i<15; i++) {
            // 호수는 항상 2 이상
            for (int j=2; j<15 ;j++) {
                // 현재 층의 이전 호수 + 아래 층의 같은 호수
                // 점화식 (테이블 채우기)
                D[i][j] = D[i][j-1] + D[i-1][j];
            }
        }

        // n은 최대 14까지
        for (int t=0; t<T; t++) {
            int k = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(D[k][n]);
        }
    }
}
