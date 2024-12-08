package Combination;

import java.util.Scanner;

public class BOJ11050 {

    static int n;
    static int k;
    static int[][] D;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        D = new int[n+1][n+1]; // 배열의 인덱스가 0부터 시작해서 n+1, D[11][11]도 가능

        for (int i=0; i<=n; i++) {
            // 테이블 초기화
            // i개 중 i개 모두 선택하는 경우의 수는 1개
            D[i][i] = 1;
            // i개 중 아무 것도 뽑지 않는 경우의 수는 1개
            D[i][0] = 1;
            // i개 중 1개 뽑는 경우의 수는 i개
            D[i][1] = i;
        }

        // bottom up (1인걸 이미 초기화 해 두었으니 2부터 시작)
        for (int i=2; i<=n; i++) {
            for (int j=1; j<i; j++) { // 선택하는 수가 전체 개수를 넘을 수 없음
                // 점화식
                D[i][j] = D[i-1][j-1] + D[i-1][j];
            }
        }
        System.out.println(D[n][k]);
    }
}
