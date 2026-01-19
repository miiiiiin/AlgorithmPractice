package DP;

import java.util.Scanner;

public class BOJ14501_RE {

    static int[] T;
    static int[] P;
    static int[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 상담 완료하는데 걸리는 기간
        T = new int[N+1];
        // 상담했을 때 받을 수 있는 금액
        P = new int[N+1];
        // i번째 날(오늘 포함)부터 퇴사일까지 벌 수 있는 최대 수익
        D = new int[N+2];

        for (int i = 1; i <= N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        // 점화식
        // D[i] = D[i+1];
        // D[i] = Math.max(D[i+1], D[i + T[i]] + P[i]);
        for (int i = N; i > 0; i--) {
            // i번째 상담을 퇴사일까지 끝낼 수 없을 때
            if (i + T[i] > N+1) {
                // 퇴사일에 벌 수 있는 수입 (퇴사일 직전 막날은 대부분 못함..)
                D[i] = D[i+1];
            } else {
                // i번째 상담을 퇴사일까지 끝낼 수 있을 때
                // 오늘 시작되는 상담을 했을 때, 퇴사일 안에 끝나는 경우
                D[i] = Math.max(D[i+1], D[i+T[i]] + P[i]);
            }
        }

        System.out.println(D[1]);
    }
}
