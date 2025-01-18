package DP;

import java.util.Scanner;

public class BOJ14501 {
    static int[] T;
    static int[] P;
    static int[] D;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 상담을 완료하는데 걸리는 기간
        T = new int[N+1];
        // 상담을 했을 때 받을 수 있는 금액
        P = new int[N+1];
        // i일에 상담했을 때 얻을 수 있는 최대 수익
        D = new int[N+2];

        for (int i=1; i<=N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        // 뒤에서부터 계산하면 미래의 최적 결과를 미리 알고 있으므로,
        // i일에 대한 최적의 결정을 내릴 수 있다.
        for (int i=N; i>0; i--) {
            // 상담 가능 기준 : 상담이 끝나는 날인 i + T[i] -1 일이 N 이하여야 한다
            // 오늘 시작되는 상담 했을 때, 퇴사일까지 끝나지 않는 경우
            if (i + T[i] > N+1) {
                D[i] = D[i+1];
            } else {
                // 상담 하거나 하지않은 경우를 비교한 최대값
                // 오늘 시작되는 상담을 했을 때, 퇴사일 안에 끝나는 경우
                D[i] = Math.max(D[i+1], P[i] + D[i +T[i]]);
            }
        }
        System.out.println(D[1]);
    }
}
